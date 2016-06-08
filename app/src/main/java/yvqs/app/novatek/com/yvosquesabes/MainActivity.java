package yvqs.app.novatek.com.yvosquesabes;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnEmpezar, btnHistorial;
    MediaPlayer mp;
    Integer calificacion,contador,acertadas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bundle = getIntent().getExtras();

        if (bundle!= null) {
            calificacion = 0;
            contador = 0;
            acertadas = 0;
        }
        else{

            calificacion = 0;
            contador = 0;
            acertadas = 0;
        }
        mp = MediaPlayer.create(this, R.raw.marimbabg);
        mp.setLooping(true);
        mp.start();

        btnEmpezar = (Button) findViewById(R.id.btnNuevoJuego);
        btnHistorial = (Button) findViewById(R.id.btnHistorial);

        btnEmpezar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.stop();
                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                intent.putExtra("Calificacion",String.valueOf(calificacion));
                intent.putExtra("Acertadas",String.valueOf(acertadas));
                intent.putExtra("Contador",String.valueOf(contador));
                startActivity(intent);
                finish();
                MainActivity.this.isDestroyed();
            }
        });

        Historial();
    }

    public void Historial(){
        btnHistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListaHistorial.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;
    }

    public void onPause() {
        super.onPause();
        if (mp.isPlaying()) {
            mp.stop();
        }
    }

    public void onResume() {
        super.onResume();
        mp.start();
    }

    public void onStop() {
        super.onStop();
        mp.stop();
        mp.release();
    }

    public void onRestart() {
        super.onRestart();
        mp = MediaPlayer.create(this, R.raw.marimbabg);
        mp.setLooping(true);
        mp.start();
    }




    public void btnShare(View view){

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, getResources().getString(R.string.Compartir));
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }


}
