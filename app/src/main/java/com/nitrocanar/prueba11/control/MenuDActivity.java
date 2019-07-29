package com.nitrocanar.prueba11.control;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nitrocanar.prueba11.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.Buffer;
import java.util.concurrent.ExecutionException;

public class MenuDActivity extends AppCompatActivity implements View.OnClickListener {


    private Button btnReadSD, btnReadIN, btnWriteSD, btnWriteIN, btnReadProg;
    private TextView textContTexto;

    private String[] PERMISSIONS = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private int REQUEST_CODE = 111;

    private boolean sdAvalible = false;
    private boolean sdAcessWrite = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_d);

        reference();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                    checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){

                ActivityCompat.requestPermissions(this, PERMISSIONS, REQUEST_CODE);

            }


        }

        String state = Environment.getExternalStorageState();

        if (state.equals(Environment.MEDIA_MOUNTED)){

            sdAvalible = true;
            sdAcessWrite = true;

        }else if (state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)){

            sdAvalible = true;
            sdAcessWrite = false;

        }else {

            sdAvalible = false;
            sdAcessWrite = false;
            
        }


    }

    private void reference() {

        btnReadSD = findViewById(R.id.btnReadSD);
        btnReadSD.setOnClickListener(this);

        btnWriteSD = findViewById(R.id.btnWriteSD);
        btnWriteSD.setOnClickListener(this);

        btnReadIN = findViewById(R.id.btnReadIntern);
        btnReadIN.setOnClickListener(this);

        btnWriteIN = findViewById(R.id.btnWriteInter);
        btnWriteIN.setOnClickListener(this);

        btnReadProg = findViewById(R.id.btnReadProg);
        btnReadProg.setOnClickListener(this);

        textContTexto = findViewById(R.id.textContTexto);


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btnReadSD:
                readSD();
                break;

            case R.id.btnWriteSD:
                writeSD();
                break;

            case R.id.btnReadIntern:
                readMemoriInternal();
                break;

            case R.id.btnWriteInter:
                writeMemoriInternal();
                break;

            case R.id.btnReadProg:
                readRes();
                break;

        }

    }

    private void readSD() {

        if (sdAvalible){

            try {

                File ruta = Environment.getExternalStorageDirectory();

                File f = new File(ruta.getAbsolutePath(), "fichero.txt");

                BufferedReader fin = new BufferedReader(new InputStreamReader(new FileInputStream(f)));

                String texto = fin.readLine();
                textContTexto.setText(texto);

                fin.close();

            }catch (Exception e){
                Log.e("Ficheros", "Error al leer el fichero desde sd");

            }
        }

    }

    private void writeSD() {

        if (sdAcessWrite && sdAvalible){
            try{


                File ruta = Environment.getExternalStorageDirectory();

                File f = new File(ruta.getAbsolutePath(), "fichero.txt");

                OutputStreamWriter fout = new OutputStreamWriter(new FileOutputStream(f));

                fout.write("Contenido del fichero SD");
                fout.close();

            }catch (Exception e){
                Log.e("Ficheros", "Error al escribir el fichero desde sd");
            }
        }

    }

    private void readRes() {

        try{

            InputStream fraw = getResources().openRawResource(R.raw.fichero);

            BufferedReader brin = new BufferedReader(new InputStreamReader(fraw));

            String linea = brin.readLine();
            textContTexto.setText(linea);

        }catch (Exception e){
            Log.e("Ficheros", "Error al leer el fichero desde recursos");
        }

    }

    private void writeMemoriInternal() {

        try {

            OutputStreamWriter fout = new OutputStreamWriter(openFileOutput("memoriaInterna.txt", Context.MODE_PRIVATE));

            fout.write("Contenido del ficheo memoria interna");
            fout.close();


        }catch (Exception e){
            Log.e("Ficheros", "Error alescribir el fichero desde la memoria interna");
        }

    }

    private void readMemoriInternal() {

        try{

            BufferedReader fin = new BufferedReader(new InputStreamReader(openFileInput("memoriaInterna.txt")));

            String texto = fin.readLine();

            textContTexto.setText(texto);

            fin.close();


        }catch (Exception e){
            Log.e("Ficheros", "Error al leer fichero desde la memoria interna");
        }

    }


}
