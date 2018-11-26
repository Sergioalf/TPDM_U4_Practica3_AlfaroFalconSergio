package com.prueba.oansc.tpdm_u4_practica3_sergioalfarofalcon;

import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Tortuga extends AsyncTask<Void, int[], int[]> {

    MainActivity ventana;
    ImageView imagen;
    TextView avance, resultado;
    int progreso;

    public Tortuga (MainActivity ventana, ImageView imagen, TextView avance, TextView resultado) {
        this.ventana = ventana;
        this.imagen = imagen;
        this.avance = avance;
        this.resultado = resultado;
        progreso = 0;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(ventana, "Inició la Tortuga", Toast.LENGTH_LONG).show();
    }

    @Override
    protected int[] doInBackground(Void... voids) {
        while (progreso < 70) {
            int[] probabilidad = new int[1];
            probabilidad[0] = obtenerAcción((int) (Math.random() * 99) + 1);
            publishProgress(probabilidad);
            progreso += probabilidad[0];
            if (progreso < 0) {
                progreso = 0;
            }
            if (progreso > 70) {
                progreso = 70;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return new int[0];
    }

    @Override
    protected void onProgressUpdate(int[]... values) {
        super.onProgressUpdate(values);
        int[] caso = values[0];
        switch (caso[0]) {
            case -6:
                imagen.setImageResource(R.drawable.down);
                break;
            case 1:
                imagen.setImageResource(R.drawable.up);
                break;
            case 3:
                imagen.setImageResource(R.drawable.upper);
        }
        if (progreso < 10) {
            avance.setText("0"+progreso);
        } else {
            avance.setText(progreso+"");
        }
        if (progreso == 70) {
            if (resultado.getText().equals(".")){
                resultado.setText("La tortuga ganó");
            }
        }
    }

    @Override
    protected void onPostExecute(int[] ints) {
        super.onPostExecute(ints);
        Toast.makeText(ventana, "Terminó la Tortuga", Toast.LENGTH_LONG).show();
    }

    private int obtenerAcción (int porcentaje) {
        if (porcentaje <= 50) {
            return 3;
        }
        if (porcentaje <= 70) {
            return -6;
        }
        if (porcentaje <= 100) {
            return 1;
        }
        return 0;
    }

}
