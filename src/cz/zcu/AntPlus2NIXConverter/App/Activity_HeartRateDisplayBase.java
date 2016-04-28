package com.example.filip.mojenahledani;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dsi.ant.plugins.antplus.pcc.AntPlusHeartRatePcc;
import com.dsi.ant.plugins.antplus.pcc.defines.DeviceState;
import com.dsi.ant.plugins.antplus.pcc.defines.EventFlag;
import com.dsi.ant.plugins.antplus.pcc.defines.RequestAccessResult;
import com.dsi.ant.plugins.antplus.pccbase.AntPluginPcc;
import com.dsi.ant.plugins.antplus.pccbase.MultiDeviceSearch;
import com.dsi.ant.plugins.antplus.pccbase.PccReleaseHandle;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.EnumSet;

public class Activity_HeartRateDisplayBase extends AppCompatActivity {

    //    protected abstract void requestAccessToPcc();
    AntPlusHeartRatePcc hrPcc = null;
    PccReleaseHandle<AntPlusHeartRatePcc> releaseHandle = null;
    TextView tv_computedHeartRate;
    TextView tv_status;
    TextView sest;
    Button start, stop;
    ArrayList<Integer> data, cisla;
    TextView pole;
    Context context;
    int i = 0;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_rate);
        context = getApplicationContext();
        requestAccessToPcc();
//        setContentView(R.layout.activity_heart_rate);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    protected void requestAccessToPcc() {
        Intent intent = getIntent();
        if (intent.hasExtra(Activity_MultiDeviceSearch.EXTRA_KEY_MULTIDEVICE_SEARCH_RESULT)) {
            // device has already been selected through the multi-device search
            MultiDeviceSearch.MultiDeviceSearchResult result = intent
                    .getParcelableExtra(Activity_MultiDeviceSearch.EXTRA_KEY_MULTIDEVICE_SEARCH_RESULT);
            releaseHandle = AntPlusHeartRatePcc.requestAccess(this, result.getAntDeviceNumber(), 0,
                    base_IPluginAccessResultReceiver, base_IDeviceStateChangeReceiver);
        } else {
            // starts the plugins UI search
            releaseHandle = AntPlusHeartRatePcc.requestAccess(this, this,
                    base_IPluginAccessResultReceiver, base_IDeviceStateChangeReceiver);
        }
        //   }
    }

    public void subscribeToHrEvents() {
                hrPcc.subscribeHeartRateDataEvent(new AntPlusHeartRatePcc.IHeartRateDataReceiver() {
                    @Override
                    public void onNewHeartRateData(final long estTimestamp, EnumSet<EventFlag> eventFlags,
                                                   final int computedHeartRate, final long heartBeatCount,
                                                   final BigDecimal heartBeatEventTime, final AntPlusHeartRatePcc.DataState dataState) {
                        // Mark heart rate with asterisk if zero detected
                        final String textHeartRate = String.valueOf(computedHeartRate)
                                + ((AntPlusHeartRatePcc.DataState.ZERO_DETECTED.equals(dataState)) ? "*" : "");
                        cisla = new ArrayList<Integer>();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tv_computedHeartRate.setText(textHeartRate);
                                data.add(Integer.parseInt(textHeartRate));
                            }
                        });
                    }
                });


    }

    public void vytiskniData(View v){
        Intent intent = new Intent(this, Pole.class);
        intent.putExtra("text", data);
        startActivity(intent);
        data.clear();
    }


    protected void showDataDisplay(String status) {
        tv_computedHeartRate = (TextView) findViewById(R.id.textView3);
        tv_status = (TextView) findViewById(R.id.textView4);
        tv_status.setText(status);
    }

    protected AntPluginPcc.IPluginAccessResultReceiver<AntPlusHeartRatePcc> base_IPluginAccessResultReceiver =
            new AntPluginPcc.IPluginAccessResultReceiver<AntPlusHeartRatePcc>() {
                //Handle the result, connecting to events on success or reporting failure to user.
                @Override
                public void onResultReceived(AntPlusHeartRatePcc result, RequestAccessResult resultCode,
                                             DeviceState initialDeviceState) {
                    showDataDisplay("Connecting...");
                    switch (resultCode) {
                        case SUCCESS:
                            hrPcc = result;
                            start = (Button) findViewById(R.id.button);
                            stop = (Button) findViewById(R.id.button2);
                            sest = (TextView) findViewById(R.id.textView6);
                            data = new ArrayList();
                            tv_status.setText("Cekam na start");
                            tv_status.setText(result.getDeviceName() + ": " + initialDeviceState);
                            start.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    subscribeToHrEvents();
                                }
                            });
                            break;
                        case CHANNEL_NOT_AVAILABLE:
                            Toast.makeText(Activity_HeartRateDisplayBase.this, "Channel Not Available", Toast.LENGTH_SHORT).show();
                            tv_status.setText("Error. Do Menu->Reset.");
                            break;
                        case ADAPTER_NOT_DETECTED:
                            Toast.makeText(Activity_HeartRateDisplayBase.this, "ANT Adapter Not Available. Built-in ANT hardware or external adapter required.", Toast.LENGTH_SHORT).show();
                            tv_status.setText("Error. Do Menu->Reset.");
                            break;
                        case BAD_PARAMS:
                            //Note: Since we compose all the params ourself, we should never see this result
                            Toast.makeText(Activity_HeartRateDisplayBase.this, "Bad request parameters.", Toast.LENGTH_SHORT).show();
                            tv_status.setText("Error. Do Menu->Reset.");
                            break;
                        case OTHER_FAILURE:
                            Toast.makeText(Activity_HeartRateDisplayBase.this, "RequestAccess failed. See logcat for details.", Toast.LENGTH_SHORT).show();
                            tv_status.setText("Error. Do Menu->Reset.");
                            break;
                        case DEPENDENCY_NOT_INSTALLED:
                            tv_status.setText("Error. Do Menu->Reset.");
                            AlertDialog.Builder adlgBldr = new AlertDialog.Builder(Activity_HeartRateDisplayBase.this);
                            adlgBldr.setTitle("Missing Dependency");
                            adlgBldr.setMessage("The required service\n\"" + AntPlusHeartRatePcc.getMissingDependencyName() + "\"\n was not found. You need to install the ANT+ Plugins service or you may need to update your existing version if you already have it. Do you want to launch the Play Store to get it?");
                            adlgBldr.setCancelable(true);
                            adlgBldr.setPositiveButton("Go to Store", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent startStore = null;
                                    startStore = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + AntPlusHeartRatePcc.getMissingDependencyPackageName()));
                                    startStore.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                                    Activity_HeartRateDisplayBase.this.startActivity(startStore);
                                }
                            });
                            adlgBldr.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });

                            final AlertDialog waitDialog = adlgBldr.create();
                            waitDialog.show();
                            break;
                        case USER_CANCELLED:
                            tv_status.setText("Cancelled. Do Menu->Reset.");
                            break;
                        case UNRECOGNIZED:
                            Toast.makeText(Activity_HeartRateDisplayBase.this,
                                    "Failed: UNRECOGNIZED. PluginLib Upgrade Required?",
                                    Toast.LENGTH_SHORT).show();
                            tv_status.setText("Error. Do Menu->Reset.");
                            break;
                        default:
                            Toast.makeText(Activity_HeartRateDisplayBase.this, "Unrecognized result: " + resultCode, Toast.LENGTH_SHORT).show();
                            tv_status.setText("Error. Do Menu->Reset.");
                            break;
                    }
                }
            };

    //Receives state changes and shows it on the status display line
    protected AntPluginPcc.IDeviceStateChangeReceiver base_IDeviceStateChangeReceiver =
            new AntPluginPcc.IDeviceStateChangeReceiver() {
                @Override
                public void onDeviceStateChange(final DeviceState newDeviceState) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv_status.setText(hrPcc.getDeviceName() + ": " + newDeviceState);
                        }
                    });


                }
            };

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Activity_HeartRateDisplayBase Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.filip.mojenahledani/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Activity_HeartRateDisplayBase Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.filip.mojenahledani/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
