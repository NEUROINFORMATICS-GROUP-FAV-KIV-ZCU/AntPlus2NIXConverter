package cz.zcu.AntPlus2NIXConverter.App;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


/***
 * Vyctovy typ modu ulozeni. Do externi pameti telefonu nebo do interni pameti telefonu.
 */
enum SaveMode {
    INTERNAL, EXTERNAL;
}

/**
 * Obsluha aktivity na ulozeni vysledneho souboru
 */
public class Activity_save extends AppCompatActivity {

    private Spinner spinner;
    private SaveMode mode;
    private Context context;
    int[] computedHeartRate;
    ArrayList<Integer> computedHeartRateList, heartBeatCounterList;
    ArrayList<Double> beatTimeList;
    TextView computedHeartRateTV, heartBeatCounterTV, beatTimeTV;
    int[] heartBeatCounter;
    double[] timeOfPreviousHeartBeat;

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    /**
     * Zjisti od systemu pristup aplikace k pameti telefonu
     * @param activity
     */
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    /**
     * Provadi akce po vytvoreni aktivity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pole);
        context = getApplicationContext();
        computedHeartRateTV = (TextView) findViewById(R.id.textView);
        heartBeatCounterTV = (TextView) findViewById(R.id.textView7);
        beatTimeTV = (TextView) findViewById(R.id.textView8);
        Intent i = getIntent();

        computedHeartRateList = i.getIntegerArrayListExtra("heartRate");
        computedHeartRateList = modifyListInt(computedHeartRateList);
        computedHeartRateTV.setText(computedHeartRateList.toString());

        heartBeatCounterList = i.getIntegerArrayListExtra("beatCounter");
        heartBeatCounterList = modifyListInt(heartBeatCounterList);
        heartBeatCounterTV.setText(heartBeatCounterList.toString());

        beatTimeList = (ArrayList<Double>) i.getSerializableExtra("beatTime");
        beatTimeList = modifyListDouble(beatTimeList);
        beatTimeTV.setText(beatTimeList.toString());
        createSpinner();

        computedHeartRate = convertToArrayInt(computedHeartRateList);
        heartBeatCounter = convertToArrayInt(heartBeatCounterList);
        timeOfPreviousHeartBeat = convertToArrayDouble(beatTimeList);
    }

    /**
     * Vytvori spinner, ve kterem uzivatel vybira, kam chce soubor ulozit, zda do externi pameti
     * telefonu nebo do interni.
     */
    public void createSpinner(){
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Do vnitrni pameti telefonu")) {
                    mode = SaveMode.INTERNAL;
                } else {
                    mode = SaveMode.EXTERNAL;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * Odstrani ze seznamu po sobe jdouci duplicity.
     * @param list seznam hodnot ze snimace
     * @return list hodnot ze snimace
     */
    public ArrayList<Integer> modifyListInt(ArrayList<Integer> list) {
        ArrayList<Integer> modified = new ArrayList<Integer>();
        modified.add(list.get(0));
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i - 1) != list.get(i)) {
                modified.add(list.get(i));
            }
        }
        return modified;
    }

    public ArrayList<Double> modifyListDouble(ArrayList<Double> list) {
        ArrayList<Double> modified = new ArrayList<Double>();
        modified.add(list.get(0));
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i - 1).equals(list.get(i)) == false) {
                modified.add(list.get(i));
            }
        }
        return modified;
    }

    public int[] convertToArrayInt(ArrayList<Integer> list){
        int[] array = new int[list.size()];
        for(int i = 0; i < array.length; i++){
            array[i] = list.get(i);
        }

        return array;
    }

    public double[] convertToArrayDouble(ArrayList<Double> list){
        double[] array = new double[list.size()];
        for(int i = 0; i < array.length; i++){
            array[i] = list.get(i);
        }

        return array;
    }


    /**
     * Ulozi soubor bud do externi nebo do interni pameti telefonu.
     * @param v
     */
    public void save(View v){
        FileOutputStream output;

        if(mode == SaveMode.INTERNAL) {
            try {
                Toast.makeText(this, "ulozeno do: " + getFilesDir().getPath(), Toast.LENGTH_LONG).show();
            } catch (Exception e) {

            }
        }else{
            this.verifyStoragePermissions(this);
            String state= Environment.getExternalStorageState();
            if (Environment.MEDIA_MOUNTED.equals(state)) {
                try {
                } catch (Exception e) {
                    Toast.makeText(this, "ulozeno do: " + Environment.getExternalStorageDirectory().getPath(), Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
