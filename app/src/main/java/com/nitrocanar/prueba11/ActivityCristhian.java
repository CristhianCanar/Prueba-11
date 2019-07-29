package com.nitrocanar.prueba11;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.usage.ExternalStorageStats;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class ActivityCristhian extends AppCompatActivity implements View.OnClickListener {

    TextView tvRespuesta;
    Button btnLeerInt, btnEscInt, btnLeerEx, btnEscEx, btnLeerPrograma;
    boolean sdDisponible = false;
    boolean sdAccesoEscritura = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acticity_cristhian);

        tvRespuesta = findViewById(R.id.tvRespuesta);
        btnLeerInt = findViewById(R.id.btnLeerMemInterna);
        btnEscInt = findViewById(R.id.btnEscribirMemInterna);
        btnLeerEx = findViewById(R.id.btnLeerExterna);
        btnEscEx = findViewById(R.id.btnEscribirExterna);
        btnLeerPrograma = findViewById(R.id.btnLeerDesdePrograma);

        btnEscEx.setOnClickListener(this);
        btnLeerEx.setOnClickListener(this);
        btnEscInt.setOnClickListener(this);
        btnLeerInt.setOnClickListener(this);
        btnLeerPrograma.setOnClickListener(this);

        String estado = Environment.getExternalStorageState();

        if (estado.equals(Environment.MEDIA_MOUNTED)){
            sdDisponible = true;
            sdAccesoEscritura = true;

        }else if (estado.equals(Environment.MEDIA_MOUNTED_READ_ONLY)){
            sdDisponible = true;
            sdAccesoEscritura = false;
        }else {
            sdAccesoEscritura = false;
            sdDisponible = false;
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new  String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE},1000);
        }


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btnLeerMemInterna:

                try {
                    BufferedReader fin =  new BufferedReader( new InputStreamReader(openFileInput("meminterna.txt")));
                    String texto = fin.readLine();
                    tvRespuesta.setText(texto);
                    fin.close();
                } catch (Exception e) {
                    Log.e("Ficheros","Error al leer el fichero de la memoria interna");
                }

                break;

            case R.id.btnEscribirMemInterna:
                try {
                    OutputStreamWriter escribir = new OutputStreamWriter(openFileOutput("meminterna.txt", Context.MODE_PRIVATE));
                    escribir.write("Contenido del fichero de memoria interna .");
                    escribir.close();

                } catch (Exception e) {
                    Log.e("Ficheros","Error al escribir el fichero en la memoria interna.");
                }
                break;

            case R.id.btnLeerExterna:

                if (sdDisponible){
                    try{
                        File ruta_sd = Environment.getExternalStorageDirectory();
                        File file = new File(ruta_sd.getAbsolutePath(),"ficherossd.txt");

                        BufferedReader fin = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                    }catch (Exception e){
                        Log.e("Ficheros","Error al leer el fichero en la SD");
                    }
                }

                break;

            case R.id.btnEscribirExterna:
                if (sdDisponible && sdAccesoEscritura){
                    try {
                        File ruta_sd = Environment.getExternalStorageDirectory();
                        File file = new File(ruta_sd.getAbsolutePath(),"ficherosd.txt");

                        OutputStreamWriter fout = new OutputStreamWriter(new FileOutputStream(file));
                    }catch (Exception e){
                        Log.e("Ficheros","Error al escribir fichero en la tarjeta SD");
                    }
                }

                break;

            case R.id.btnLeerDesdePrograma:
                try {
                    InputStream readerraw = getResources().openRawResource(R.raw.ficheroraw);

                    BufferedReader reader = new BufferedReader(new InputStreamReader(readerraw));

                    String linea = reader.readLine();
                    tvRespuesta.setText(linea);

                    readerraw.close();
                } catch (Exception e){
                    Log.e("Fichero","Error al leer recurso del programa");
                }
                break;
                default:
                    break;
        }


    }
}
