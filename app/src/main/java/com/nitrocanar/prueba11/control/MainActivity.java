package com.nitrocanar.prueba11.control;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.nitrocanar.prueba11.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnDavid, btnCristhian;
    private Intent intent;

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

                intent = new Intent(MainActivity.this, MenuDActivity.class);
                startActivity(intent);

                break;

            case R.id.btnCristhian:


                break;
        }
    }
}
