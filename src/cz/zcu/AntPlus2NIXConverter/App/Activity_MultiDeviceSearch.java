package com.example.filip.mojenahledani;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dsi.ant.plugins.antplus.pcc.AntPlusFitnessEquipmentPcc;
import com.dsi.ant.plugins.antplus.pcc.MultiDeviceSearch;
import com.dsi.ant.plugins.antplus.pcc.MultiDeviceSearch.RssiSupport;
import com.dsi.ant.plugins.antplus.pcc.defines.DeviceType;
import com.dsi.ant.plugins.antplus.pcc.defines.RequestAccessResult;
import com.dsi.ant.plugins.antplus.pccbase.MultiDeviceSearch.MultiDeviceSearchResult;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;

/*class ArrayAdapter1 extends ArrayAdapter<MultiDeviceSearchResult>{


    public ArrayAdapter1(Context context,
                         ArrayList<MultiDeviceSearchResult> data) {
        super(context, R.layout.layout_multidevice_searchresult, data);
        mData = data;
        mDeviceTypes = context.getResources().getStringArray(R.array.device_types);;
    }
}*/

public class Activity_MultiDeviceSearch extends AppCompatActivity {

/*    public class MultiDeviceSearchResultWithRSSI
    {*/
 /*       public int mRSSI = Integer.MIN_VALUE;
    }*/

    public static final String EXTRA_KEY_MULTIDEVICE_SEARCH_RESULT = "com.example.filip.mojenahledani.results";
    public static final String BUNDLE_KEY = "com.example.filip.mojenahledani.bundle";
    public static final String FILTER_KEY = "com.example.filip.mojenahledani.filter";
    public static final int RESULT_SEARCH_STOPPED = RESULT_FIRST_USER;
    public static final DeviceType srdce = DeviceType.HEARTRATE;

    Context mContext;
    TextView mStatus;
    Button dalsi;

    ListView mFoundDevicesList;
    ArrayList<MultiDeviceSearchResult> mFoundDevices = new ArrayList<MultiDeviceSearchResult>();
    ArrayAdapter mFoundAdapter;

    ListView mConnectedDevicesList;
    ArrayList<MultiDeviceSearchResult> mConnectedDevices = new ArrayList<MultiDeviceSearchResult>();
    ArrayAdapter mConnectedAdapter;

    public MultiDeviceSearchResult mDevice;
    MultiDeviceSearch mSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mContext = getApplicationContext();
        mStatus = (TextView) findViewById(R.id.textView2);

        mFoundDevicesList = (ListView) findViewById(R.id.listView);

        mFoundAdapter = new ArrayAdapter(this, R.layout.simple_row, mFoundDevices);
        mFoundDevicesList.setAdapter(mFoundAdapter);

        mFoundDevicesList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
      //          Toast.makeText(mContext, mFoundDevices.get(position), Toast.LENGTH_SHORT).show();
            launchConnection(mFoundDevices.get(position));
            }
        });

        Intent i = getIntent();
        Bundle args = i.getBundleExtra(BUNDLE_KEY);
        @SuppressWarnings("unchecked")
        EnumSet<DeviceType> devices = EnumSet.of(srdce);


        // start the multi-device search
        mSearch = new MultiDeviceSearch(this, devices, mCallback);

    }

    public void launchConnection(MultiDeviceSearchResult result){
        Intent intent = new Intent(this, Activity_HeartRateDisplayBase.class);
        intent.putExtra(EXTRA_KEY_MULTIDEVICE_SEARCH_RESULT, result);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private MultiDeviceSearch.SearchCallbacks mCallback = new MultiDeviceSearch.SearchCallbacks()
    {
        /**
         * Called when a device is found. Display found devices in connected and
         * found lists
         */
        public void onDeviceFound(final MultiDeviceSearchResult deviceFound)
        {
          /*  final MultiDeviceSearchResultWithRSSI result = new MultiDeviceSearchResultWithRSSI();
            result.*/mDevice = deviceFound;

            runOnUiThread(new Runnable()
            {
                @Override
                public void run()
                {
                    mFoundAdapter.add(mDevice);
                    mFoundAdapter.notifyDataSetChanged();
                }
            });

        }


        /**
         * The search has been stopped unexpectedly
         */
        public void onSearchStopped(RequestAccessResult reason)
        {
            Intent result = new Intent();
            result.putExtra(EXTRA_KEY_MULTIDEVICE_SEARCH_RESULT, reason.getIntValue());
            setResult(RESULT_SEARCH_STOPPED, result);
            finish();
        }

        @Override
        public void onSearchStarted(RssiSupport supportsRssi) {
            if(supportsRssi == RssiSupport.UNAVAILABLE)
            {
                Toast.makeText(mContext, "Rssi information not available.", Toast.LENGTH_SHORT).show();
            } else if(supportsRssi == RssiSupport.UNKNOWN_OLDSERVICE)
            {
                Toast.makeText(mContext, "Rssi might be supported. Please upgrade the plugin service.", Toast.LENGTH_SHORT).show();
            }
        }
    };

}
