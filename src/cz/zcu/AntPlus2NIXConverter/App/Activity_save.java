package com.example.filip.mojenahledani;

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


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import cz.zcu.AntPlus2NIXConverter.Data.OdMLData;
import cz.zcu.AntPlus2NIXConverter.Profiles.AntBikeSpeed;
import cz.zcu.AntPlus2NIXConverter.Profiles.AntHeartRate;

/***
 * Vyctovy typ modu ulozeni. Do externi pameti telefonu nebo do interni pameti telefonu.
 */
enum ModUlozeni{
    INTERNI, EXTERNI;
}

/**
 * Obsluha aktivity na ulozeni vysledneho souboru
 */
public class Activity_save extends AppCompatActivity {

    private Spinner spinner;
    private ModUlozeni mode;
    private Context context;
    int[] vyslednePole;
    TextView pole;
    AntBikeSpeed profil;
    OdMLData odml;
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
        pole = (TextView) findViewById(R.id.textView);

        Intent i = getIntent();
        vyslednePole = upravSeznam(i.getIntegerArrayListExtra("text"));
        vytvorSpinner();

    }

    /**
     * Vytvori spinner, ve kterem uzivatel vybira, kam chce soubor ulozit, zda do externi pameti
     * telefonu nebo do interni.
     */
    public void vytvorSpinner(){
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Do vnitrni pameti telefonu")) {
                    mode = ModUlozeni.INTERNI;
                    Toast.makeText(context, "Vybrano interni", Toast.LENGTH_LONG).show();
                } else {
                    mode = ModUlozeni.EXTERNI;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * Odstrani ze seznamu po sobe jdouci duplicity a ze seznamu vytvori pole.
     * @param list seznam hodnot ze snimace
     * @return pole hodnot ze snimace
     */
    public int[] upravSeznam(ArrayList<Integer> list){
        ArrayList<Integer> nove = new ArrayList<Integer>();
        nove.add(list.get(0));
        for(int i = 1; i < list.size(); i++){
            if(list.get(i-1) != list.get(i)){
                nove.add(list.get(i));
            }
        }

        pole.setText(nove.toString());
        int[] pole = new int[nove.size()];

        for(int i = 0; i < pole.length; i++){
            pole[i] = nove.get(i);
        }

        return pole;
    }

    /**
     * Ulozi soubor bud do externi nebo do interni pameti telefonu.
     * @param v
     */
    public void uloz(View v){
        FileOutputStream output;
        String filename = "Ahoj.txt";
        String retez = "Zkouskaaa";
        odml = new OdMLData();
        heartBeatCounter = new int[5];
        timeOfPreviousHeartBeat = new double[5];

        if(mode == ModUlozeni.INTERNI) {
            try {
                profil = new AntBikeSpeed(heartBeatCounter, vyslednePole, odml);
                output = openFileOutput(filename, Context.MODE_PRIVATE);
                output.write(retez.getBytes());

                output.close();
                profil.createFile(filename);

                Toast.makeText(this, getFilesDir().getPath(), Toast.LENGTH_LONG).show();
            } catch (Exception e) {

            }
        }else{
            this.verifyStoragePermissions(this);
            String state= Environment.getExternalStorageState();
            if (Environment.MEDIA_MOUNTED.equals(state)) {
                String filePath = Environment.getExternalStorageDirectory().getPath() + "AntHeartRate.h5";
                File f = new File(Environment.getExternalStorageDirectory(), "file1.txt");

                Toast.makeText(this, Environment.getExternalStorageDirectory().getPath(), Toast.LENGTH_LONG).show();
                try {
                    profil = new AntBikeSpeed(heartBeatCounter, vyslednePole, odml);
                    profil.createFile(filePath);
                    FileOutputStream fs = new FileOutputStream(f);
                    PrintWriter pw = new PrintWriter(f);
                    pw.println("Hi , How are you");
                    pw.println("Hello");
                    pw.flush();
                    pw.close();
                    fs.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
