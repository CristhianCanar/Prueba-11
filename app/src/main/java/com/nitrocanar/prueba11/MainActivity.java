package com.nitrocanar.prueba11;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnDavid, btnCristhian;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDavid = findViewById(R.id.btnDavid);
        btnDavid.setOnClickListener(this);

        btnCristhian = findViewById(R.id.btnCristhian);
        btnCristhian.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.btnDavid :

                break;

            case R.id.btnCristhian:


                break;
        }
    }
}
