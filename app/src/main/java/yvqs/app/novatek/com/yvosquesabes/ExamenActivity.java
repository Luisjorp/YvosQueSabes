package yvqs.app.novatek.com.yvosquesabes;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import Clases.*;

public class ExamenActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn1, btn2, btn3, btn4;
    TextView textoPreguta, textoCalificacion, txtAcertadas;
    EPreguntas p = null;
    Integer contador = 0, acertadas = 0;
    String categoria;
    Integer calificacion = 0;
    ImageView fondo = null;
    MediaPlayer background;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examen);
        fondo = (ImageView) findViewById(R.id.imageView);
        Map<String, Integer> map = new HashMap<String, Integer>();
        txtAcertadas = (TextView) findViewById(R.id.txtAcertadas);
        textoCalificacion = (TextView) findViewById(R.id.txtCalificacion2);

        background = MediaPlayer.create(this, R.raw.back);
        background.start();

        Bundle bundle = getIntent().getExtras();
        if(bundle!= null){
            contador = Integer.valueOf(bundle.getString("Contador"));
            categoria = String.valueOf(bundle.getString("Categoria"));
            acertadas = Integer.valueOf(bundle.getString("Acertadas"));
            calificacion = Integer.valueOf(bundle.getString("Calificacion"));

            switch (categoria) {

                case "DEPORTES":
                    map.put("deportes", R.drawable.deportesbann2);
                    fondo.setImageResource(map.get("deportes"));

                    break;
                case "GEOGRAFÍA":
                    map.put("geografia", R.drawable.geografiabann2);
                    fondo.setImageResource(map.get("geografia"));
                    break;
                case "HISTORIA":
                    map.put("historia", R.drawable.historiabann2);
                    fondo.setImageResource(map.get("historia"));
                    break;
                case "ENTRETENIMIENTO":
                    map.put("entretenimiento", R.drawable.entretenbann2);
                    fondo.setImageResource(map.get("entretenimiento"));
                    break;

            }

            txtAcertadas.setText("R/ ACERTADAS: " + String.valueOf(acertadas) + "/10");
            textoCalificacion.setText("PUNTOS: " + String.valueOf(calificacion) + "/100");

        }else{
            contador = 0;
            calificacion = 0;
            acertadas = 0;
        }

        btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(this);
        btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(this);
        btn3 = (Button) findViewById(R.id.btn3);
        btn3.setOnClickListener(this);
        btn4 = (Button) findViewById(R.id.btn4);
        btn4.setOnClickListener(this);

        btn1.setEnabled(true);
        btn2.setEnabled(true);
        btn3.setEnabled(true);
        btn4.setEnabled(true);

        textoPreguta = (TextView) findViewById(R.id.pregunta);
        generarPregunta();
    }

    private void generarPregunta() {

            DBPreguntas objPreguntas = new DBPreguntas(this);
            ArrayList<EPreguntas> preg = objPreguntas.getPregunta(String.valueOf(categoria));

            if (!preg.isEmpty()) {

                int aleatorio = generarAleatorio(preg.size());
                p = preg.get(aleatorio);

                ArrayList<String> mezcladas = new ArrayList<String>();
                mezcladas.add(p.getCorrecta());
                mezcladas.add(p.getOpcion1());
                mezcladas.add(p.getOpcion2());
                mezcladas.add(p.getOpcion3());

                final long seed = System.nanoTime();
                Collections.shuffle(mezcladas, new Random(seed));

                String[] temporal = new String[mezcladas.size()];
                temporal = mezcladas.toArray(temporal);

                textoPreguta.setText(p.getPregunta());

                btn1.setText(temporal[0]);
                btn2.setText(temporal[1]);
                btn3.setText(temporal[2]);
                btn4.setText(temporal[3]);

            }

    }

    private int generarAleatorio(int cantidad) {
        return (int) (Math.random() * cantidad);
    }

    @Override
    public void onClick(View v) {

        Button btn = (Button) v;
        background.stop();

        btn1.setEnabled(false);
        btn2.setEnabled(false);
        btn3.setEnabled(false);
        btn4.setEnabled(false);

        contador = contador +1;
            if (contador<10){

                if (p.getCorrecta().equals(btn.getText())) {

                    mp = MediaPlayer.create(this, R.raw.correct);
                    mp.start();
                    Toast.makeText(getApplicationContext(), "Respuesta Correcta", Toast.LENGTH_SHORT).show();
                    calificacion = calificacion + 10;
                    acertadas = acertadas + 1;
                    txtAcertadas.setText("R/ ACERTADAS: " + String.valueOf(acertadas) + "/10");
                    textoCalificacion.setText("PUNTOS: " + String.valueOf(calificacion) + "/100");

                } else {

                    mp = MediaPlayer.create(this, R.raw.incorrect);
                    mp.start();
                    Toast.makeText(getApplicationContext(), "Respuesta Incorrecta", Toast.LENGTH_SHORT).show();

                }

                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
                    public void onCompletion(MediaPlayer player) {
                        player.release();
                        Intent intent = new Intent(ExamenActivity.this,MenuActivity.class);
                        intent.putExtra("Contador",String.valueOf(contador));
                        intent.putExtra("Calificacion",String.valueOf(calificacion));
                        intent.putExtra("Acertadas",String.valueOf(acertadas));
                        startActivity(intent);
                        finish();
                        ExamenActivity.this.isDestroyed();
                    }
                });


            } else{

                if (p.getCorrecta().equals(btn.getText())) {

                    mp = MediaPlayer.create(this, R.raw.correct);
                    mp.start();
                    Toast.makeText(getApplicationContext(), "Respuesta Correcta", Toast.LENGTH_SHORT).show();
                    calificacion = calificacion + 10;
                    acertadas = acertadas + 1;
                    txtAcertadas.setText("R/ ACERTADAS: " + String.valueOf(acertadas) + "/10");
                    textoCalificacion.setText("PUNTOS: " + String.valueOf(calificacion) + "/100");

                } else {

                    mp = MediaPlayer.create(this, R.raw.incorrect);
                    mp.start();
                    Toast.makeText(getApplicationContext(), "Respuesta Incorrecta", Toast.LENGTH_SHORT).show();

                }

                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
                    public void onCompletion(MediaPlayer player) {
                        player.release();
                    }
                });

                Calendar c = Calendar.getInstance();
                SimpleDateFormat fch = new SimpleDateFormat("dd-MM-yyyy");
                String fecha = fch.format(c.getTime());
                SimpleDateFormat hr = new SimpleDateFormat("HH:mm");
                String hora = hr.format(c.getTime());



                ArrayList<EHistorial> historial = new ArrayList<EHistorial>(Arrays.asList(
                        new EHistorial(String.valueOf(fecha),String.valueOf(hora),String.valueOf(calificacion))
                ));

                DBPreguntas helper = new DBPreguntas(ExamenActivity.this);

                for (int i=0; i<historial.size();i++){

                    EHistorial histo = new EHistorial();
                    histo = historial.get(i);

                    helper.InsertarRegistro(histo);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

                Intent intent = new Intent(ExamenActivity.this,ResultadoActivity.class);
                intent.putExtra("Calificacion",String.valueOf(calificacion));
                intent.putExtra("Acertadas",String.valueOf(acertadas));
                intent.putExtra("Contador",String.valueOf(contador));
                startActivity(intent);
                finish();
                ExamenActivity.this.isDestroyed();

            }
    }

    @Override
    public void onBackPressed() {
        AlertDialog salirD = new AlertDialog.Builder(this).create();
        salirD.setTitle("Abandonar");
        salirD.setMessage("Si abandonas tu progreso no será registrado ¿Deseas salir realmente?");
        salirD.setIcon(R.drawable.alerta);

        salirD.setButton(DialogInterface.BUTTON_POSITIVE, "SIMÓN", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(ExamenActivity.this, MainActivity.class);
                intent.putExtra("Calificacion",String.valueOf(calificacion));
                intent.putExtra("Acertadas",String.valueOf(acertadas));
                intent.putExtra("Contador",String.valueOf(contador));
                startActivity(intent);
                finish();
                ExamenActivity.this.isDestroyed();
            }
        });

        salirD.setCancelable(true);
        salirD.setButton(DialogInterface.BUTTON_NEGATIVE, "NEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        salirD.show();
    }

    public void onPause() {
        super.onPause();
        if (background.isPlaying()) {
            background.stop();
        }
    }

    public void onResume() {
        super.onResume();
        background.start();
    }

    public void onStop() {
        super.onStop();
        background.stop();
    }

}
