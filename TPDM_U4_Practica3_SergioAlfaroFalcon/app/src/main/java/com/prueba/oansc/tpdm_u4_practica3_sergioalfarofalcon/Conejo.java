package com.prueba.oansc.tpdm_u4_practica3_sergioalfarofalcon;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Conejo extends AsyncTask<Void, int[], int[]> {

    MainActivity ventana;
    ImageView imagen;
    TextView avance, resultado;
    int progreso;

    public Conejo (MainActivity ventana, ImageView imagen, TextView avance, TextView resultado) {
        this.ventana = ventana;
        this.imagen = imagen;
        this.avance = avance;
        this.resultado = resultado;
        progreso = 0;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(ventana, "Inició la liebre", Toast.LENGTH_LONG).show();
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
            case -12:
                imagen.setImageResource(R.drawable.downer);
                break;
            case -2:
                imagen.setImageResource(R.drawable.down);
                break;
            case 0:
                imagen.setImageResource(R.drawable.moon);
                break;
            case 1:
                imagen.setImageResource(R.drawable.up);
                break;
            case 9:
                imagen.setImageResource(R.drawable.upper);
        }
        if (progreso < 10) {
            avance.setText("0"+progreso);
        } else {
            avance.setText(progreso+"");
        }
        if (progreso == 70) {
            if (resultado.getText().equals(".")){
                resultado.setText("La liebre ganó");
            }
        }
    }

    @Override
    protected void onPostExecute(int[] ints) {
        super.onPostExecute(ints);
        Toast.makeText(ventana, "Terminó la liebre", Toast.LENGTH_LONG).show();
    }

    private int obtenerAcción (int porcentaje) {
        if (porcentaje <= 20) {
            return 0;
        }
        if (porcentaje <= 40) {
            return 9;
        }
        if (porcentaje <= 50) {
            return -12;
        }
        if (porcentaje <= 80) {
            return 1;
        }
        if (porcentaje <= 100) {
            return -2;
        }
        return 0;
    }

}
