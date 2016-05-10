package com.example.filip.mojenahledani;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
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

/***
 * Trida, ktera zajistuje, jake informace chceme zobrazit v ListView nalezenych ANT+ snimacu.
 * Vlastne konvertuje objekty z ArrayListu do View, ktery se pote da zobrazit v ListView.
 */
class ArrayAdapter1 extends ArrayAdapter<MultiDeviceSearchResult>{

    /* seznam nalezenych zarizeni */
    private ArrayList<MultiDeviceSearchResult> mData;

    /**
     * Konstruktor na vytvoreni adapteru.
     * @param context aktualni obrazovka
     * @param data seznam nalezenych zarizeni
     */
    public ArrayAdapter1(Context context, ArrayList<MultiDeviceSearchResult> data) {
        super(context, R.layout.simple_row, data);
        mData = data;
    }

    /**
     * Metoda prevzata z rodicovske tridy.
     * @param position pozice nalezeneho zarizeni v seznamu
     * @param convertView upraveny View
     * @param parent
     * @return upraveny view, s formatem, jaky bude zobrazovat ListView
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        if (convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.simple_row, null);
        }

        MultiDeviceSearchResult result = mData.get(position);
        if(result != null){
            TextView view = (TextView) convertView.findViewById(R.id.rowTextView);
            view.setText(result.getAntDeviceType().toString() + " " + result.getAntDeviceNumber());
        }

        return convertView;

    }
}

/**
 * Obsluha hlavni aktivity, ktera se zobrazi po spusteni aplikace.
 */

public class Activity_MultiDeviceSearch extends AppCompatActivity {

    public static final String EXTRA_KEY_MULTIDEVICE_SEARCH_RESULT = "com.example.filip.mojenahledani.results";
    public static final String BUNDLE_KEY = "com.example.filip.mojenahledani.bundle";
    public static final String FILTER_KEY = "com.example.filip.mojenahledani.filter";
    public static final int RESULT_SEARCH_STOPPED = RESULT_FIRST_USER;
    public static final DeviceType type = DeviceType.HEARTRATE;

    Context mContext;

    ListView mFoundDevicesList;
    ArrayList<MultiDeviceSearchResult> mFoundDevices = new ArrayList<MultiDeviceSearchResult>();
    ArrayAdapter1 mFoundAdapter;

    public MultiDeviceSearchResult mDevice;
    MultiDeviceSearch mSearch;


    /**
     * Provadi akce po vytvoreni aktivity
     * @param savedInstanceState
     */
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
        mFoundDevicesList = (ListView) findViewById(R.id.list);

        mFoundAdapter = new ArrayAdapter1(this, mFoundDevices);
        mFoundDevicesList.setAdapter(mFoundAdapter);

        /* Po kliknuti na nalezene zarizeni se otevre nova aktivita */
        mFoundDevicesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                launchConnection(mFoundDevices.get(position));
            }
        });

        Intent i = getIntent();
        Bundle args = i.getBundleExtra(BUNDLE_KEY);
        EnumSet<DeviceType> devices = EnumSet.of(type);


        /* Spusti hledani */
        mSearch = new MultiDeviceSearch(this, devices, mCallback);
    }

    /**
     * Spusti novou aktivitu.
     * @param result vybrany snimac
     */
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
         * Zavola se, kdyz je nalezen nejaky snimac.
         */
        public void onDeviceFound(final MultiDeviceSearchResult deviceFound)
        {
            mDevice = deviceFound;

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mFoundAdapter.add(mDevice);
                    mFoundAdapter.notifyDataSetChanged();
                }
            });

        }


        /**
         * Hledani se neocekavane zastavilo
         */
        public void onSearchStopped(RequestAccessResult reason)
        {
            Intent result = new Intent();
            result.putExtra(EXTRA_KEY_MULTIDEVICE_SEARCH_RESULT, reason.getIntValue());
            setResult(RESULT_SEARCH_STOPPED, result);
            finish();
        }

        /**
         * Spusti se pri zacatku hledani. Rssi udava vzdalenost snimacu od telefonu, my nevyuzivame.
         * @param supportsRssi
         */
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

