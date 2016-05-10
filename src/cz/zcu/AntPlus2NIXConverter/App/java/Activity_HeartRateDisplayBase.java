package com.example.filip.mojenahledani;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
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

/***
 * Obsluha aktivity zobrazujici tep.
 */
public class Activity_HeartRateDisplayBase extends AppCompatActivity {

    AntPlusHeartRatePcc hrPcc = null;
    PccReleaseHandle<AntPlusHeartRatePcc> releaseHandle = null;
    TextView tv_computedHeartRate, tv_status, tv_heartBeatCounter, tv_beatTime;
    Button startBT, stopBT, endBT;
    ArrayList<Integer> heartRateList, beatCounterList;
    ArrayList<Double> beatTimeList;
    boolean isRecieving;
    Chronometer time;
    Context context;
    private GoogleApiClient client;

    /**
     * Provadi akce po vytvoreni aktivity.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_rate);
        context = getApplicationContext();
        requestAccessToPcc();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * Prevezme vybrany snimac z predchozi aktivity a vytvori pristup k datum vysilanym ze snimace.
     */
    protected void requestAccessToPcc() {
        Intent intent = getIntent();
        MultiDeviceSearch.MultiDeviceSearchResult result = intent
                    .getParcelableExtra(Activity_MultiDeviceSearch.EXTRA_KEY_MULTIDEVICE_SEARCH_RESULT);
        releaseHandle = AntPlusHeartRatePcc.requestAccess(this, result.getAntDeviceNumber(), 0,
                base_IPluginAccessResultReceiver, base_IDeviceStateChangeReceiver);
    }

    /**
     * Prijima a zobrazuje do TextView data ze snimace.
     */
    public void subscribeToHrEvents() {
            hrPcc.subscribeHeartRateDataEvent(new AntPlusHeartRatePcc.IHeartRateDataReceiver() {
                @Override
                public void onNewHeartRateData(final long estTimestamp, EnumSet<EventFlag> eventFlags,
                                               final int computedHeartRate, final long heartBeatCount,
                                               final BigDecimal heartBeatEventTime, final AntPlusHeartRatePcc.DataState dataState) {
                    final String textHeartRate = String.valueOf(computedHeartRate);
                    final String textHeartBeatCount = String.valueOf(heartBeatCount);
                    final String textHeartBeatEventTime = String.valueOf(heartBeatEventTime);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (isRecieving) {
                                tv_computedHeartRate.setText(textHeartRate);
                                heartRateList.add(Integer.parseInt(textHeartRate));
                                tv_heartBeatCounter.setText(textHeartBeatCount);
                                beatCounterList.add(Integer.parseInt(textHeartBeatCount));
                                tv_beatTime.setText(textHeartBeatEventTime);
                                beatTimeList.add(Double.parseDouble(textHeartBeatEventTime));
                            }
                        }
                    });
                }
            });



    }

    /***
     * Provede se po stisknuti tlacitka Stop.
     * Zastavi prenos dat ze snimace a nabidne ukonceni treninku.
     * @param v
     */
    public void stopRecieving(View v){
        isRecieving = false;
        time.stop();
        startBT.setText("Novy start");
        endBT.setVisibility(View.VISIBLE);
        tv_status.setText("Prenos zastaven");
        tv_computedHeartRate.setText("Trenink ukoncen");

    }

    /**
     * Po stisku tlacitka na ukonceni treninku spusti novou aktivitu a zistany seznam hodnot preda nove aktivite.
     * @param v
     */
    public void endPractice(View v){
        Intent intent = new Intent(this, Activity_save.class);
        intent.putExtra("heartRate", heartRateList);
        intent.putExtra("beatCounter", beatCounterList);
        intent.putExtra("beatTime", beatTimeList);
        startActivity(intent);
    }

    public void initialize(){
        startBT = (Button) findViewById(R.id.button);
        stopBT = (Button) findViewById(R.id.button2);
        endBT = (Button) findViewById(R.id.button4);
        heartRateList = new ArrayList();
        beatCounterList = new ArrayList();
        beatTimeList = new ArrayList();
        tv_status = (TextView) findViewById(R.id.textView4);
        tv_computedHeartRate = (TextView) findViewById(R.id.textView2);
        tv_heartBeatCounter = (TextView) findViewById(R.id.textView5);
        tv_beatTime = (TextView) findViewById(R.id.textView6);
    }

    /**
     * Spousti prijimani dat ze snimace, kdyz je naramek aktivni a pripojen.
     */
    protected AntPluginPcc.IPluginAccessResultReceiver<AntPlusHeartRatePcc> base_IPluginAccessResultReceiver =
            new AntPluginPcc.IPluginAccessResultReceiver<AntPlusHeartRatePcc>() {
                //Handle the result, connecting to events on success or reporting failure to user.
                @Override
                public void onResultReceived(AntPlusHeartRatePcc result, RequestAccessResult resultCode,
                                             DeviceState initialDeviceState) {
                    switch (resultCode) {
                        case SUCCESS:
                            final String stav = initialDeviceState.toString();
                            hrPcc = result;
                            initialize();
                            startBT.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    endBT.setVisibility(View.INVISIBLE);
                                    heartRateList.clear();
                                    beatTimeList.clear();
                                    beatCounterList.clear();
                                    isRecieving = true;
                                    time = (Chronometer) findViewById(R.id.chronometer);
                                    time.setBase(SystemClock.elapsedRealtime());
                                    tv_status.setText(stav);
                                    time.start();
                                    subscribeToHrEvents();
                                }
                            });
                            break;
                        default:
                            Toast.makeText(Activity_HeartRateDisplayBase.this, "Unrecognized result: " + resultCode, Toast.LENGTH_SHORT).show();
                            tv_status.setText("Error. Do Menu->Reset.");
                            break;
                    }
                }
            };

    /**
     * Zaobrazuje zmenu stavu snimace.
     */
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
