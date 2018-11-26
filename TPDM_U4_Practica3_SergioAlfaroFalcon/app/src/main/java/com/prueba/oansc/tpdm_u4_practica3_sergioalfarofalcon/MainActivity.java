package com.prueba.oansc.tpdm_u4_practica3_sergioalfarofalcon;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView imgConejo, imgTortuga;
    TextView txtConejo, txtTorutga, resultado;
    Tortuga tortuga;
    Conejo conejo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgConejo = findViewById(R.id.imgConejo);
        imgTortuga = findViewById(R.id.imgTortuga);
        txtConejo = findViewById(R.id.tctConejo);
        txtTorutga = findViewById(R.id.txtTortuga);
        resultado = findViewById(R.id.resultado);

        tortuga = new Tortuga(MainActivity.this, imgTortuga, txtTorutga, resultado);
        conejo = new Conejo(MainActivity.this, imgConejo, txtConejo, resultado);

        tortuga.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        conejo.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

}
