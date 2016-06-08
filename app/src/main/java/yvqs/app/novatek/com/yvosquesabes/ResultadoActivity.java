package yvqs.app.novatek.com.yvosquesabes;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ResultadoActivity extends AppCompatActivity {

    TextView textoCali;
    Button btnReiniciar, btnHistorial, btnShare2;
    Integer calificacion,contador,acertadas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);


        textoCali =  (TextView) findViewById(R.id.calificacionfinal);
        btnReiniciar = (Button) findViewById(R.id.btnReiniciar);
        btnHistorial = (Button) findViewById(R.id.btnHistorial);
        btnShare2 = (Button) findViewById(R.id.btnShareResultado);


        Bundle bundle = getIntent().getExtras();

        if (bundle!= null) {
            calificacion = Integer.valueOf(bundle.getString("Calificacion"));
            contador = Integer.valueOf(bundle.getString("Contador"));
            acertadas = Integer.valueOf(bundle.getString("Acertadas"));

            if(calificacion>=90) {

                MediaPlayer mp = MediaPlayer.create(this, R.raw.aplausos);
                mp.start();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer player) {
                        player.release();
                    }
                });
                textoCali.setText("¡" + String.valueOf(calificacion) + " PUNTOS!. Claramente llevas un tamal de chipilin amarrado a la cintura.");

            }else if(calificacion>=70 &&  calificacion<90){

                MediaPlayer mp = MediaPlayer.create(this, R.raw.aplausos);
                mp.start();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
                    public void onCompletion(MediaPlayer player) {
                        player.release();
                    }
                });
                textoCali.setText(String.valueOf(calificacion) + " puntos. Tus raices chapinas salen a relucir con éste resultado.");

            } else if(calificacion>=50 && calificacion<70) {

                MediaPlayer mp = MediaPlayer.create(this, R.raw.aplausos);
                mp.start();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer player) {
                        player.release();
                    }
                });
                textoCali.setText(String.valueOf(calificacion) + " puntos, lamentamos decirte que no sos tan chapín como crees.");

            } else if(calificacion>=30 && calificacion<50) {

                MediaPlayer mp = MediaPlayer.create(this, R.raw.malo);
                mp.start();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer player) {
                        player.release();
                    }
                });
                textoCali.setText(String.valueOf(calificacion) + " puntos, a lo mejor y decir que sos chapin no es lo tuyo.");

            }else if(calificacion>10 && calificacion<30) {

                MediaPlayer mp = MediaPlayer.create(this, R.raw.malo);
                mp.start();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer player) {
                        player.release();
                    }
                });
                textoCali.setText(String.valueOf(calificacion) + "puntos. Pfff ¿Chapín vos? Jajaja.");

            }else if(calificacion<=10) {

            MediaPlayer mp = MediaPlayer.create(this, R.raw.malo);
            mp.start();
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer player) {
                    player.release();
                }
            });
            textoCali.setText(String.valueOf(calificacion) + "puntos.  Hummm...");

            }

        }else{
            calificacion = 0;
        }

        Reiniciar();
        Historial();
    }

   public void Reiniciar(){
       btnReiniciar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(ResultadoActivity.this, MainActivity.class);
               intent.putExtra("Calificacion",String.valueOf(calificacion));
               intent.putExtra("Acertadas",String.valueOf(acertadas));
               intent.putExtra("Contador",String.valueOf(contador));
               startActivity(intent);
               finish();
               ResultadoActivity.this.isDestroyed();
           }
       });
   }

    public void Historial(){
        btnHistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultadoActivity.this, ListaHistorial.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;
    }

    public void btnShareResultado(View view){

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Acabo de obtener " + calificacion + " puntos en la nueva aplicación" +
                " llamada 'Y Vos ¿Qué Sabés?'. Descargala vos también y poné a prueba tu conocimiento sobre las distintas " +
                "categorias de preguntas que te mostrará la Chapin-Ruleta acerca de Guatemala. No esperés más, " +
                "demostrá que tan chapín sos mano.");
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

}
