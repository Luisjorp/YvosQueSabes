package yvqs.app.novatek.com.yvosquesabes;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import Clases.MyDialog;


public class MenuActivity extends AppCompatActivity {

    MediaPlayer mp;
    private ImageView gif;
    int calificacion,contador,acertadas;
    public static String categoria;
    MediaPlayer sonido;
    AnimationDrawable mianimacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        sonido = MediaPlayer.create(MenuActivity.this, R.raw.seleccion);

        Bundle bundle = getIntent().getExtras();
        if(bundle!= null){
            contador = Integer.valueOf(bundle.getString("Contador"));
            calificacion = Integer.valueOf(bundle.getString("Calificacion"));
            acertadas = Integer.valueOf(bundle.getString("Acertadas"));
        }

        mp = MediaPlayer.create(this, R.raw.marimbabg);
        mp.setLooping(true);
        mp.start();
        mp.setVolume(1.0f, 1.0f);



        gif = (ImageView) findViewById(R.id.imagevuewPrueba);
        gif.post(new Runnable() {
            @Override
            public void run() {
                mianimacion = ((AnimationDrawable) gif.getBackground());
                mianimacion.start();

            }
        });

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

    public void mostrarCategoria(View v){

        mianimacion.stop();

        int frameNumber;
// Get the frame of the animation
        Drawable currentFrame, checkFrame;
        currentFrame = mianimacion.getCurrent();
// Checks the position of the frame
        for (int i = 0; i < mianimacion.getNumberOfFrames(); i++) {
            checkFrame = mianimacion.getFrame(i);
            if (checkFrame == currentFrame) {
                frameNumber = i;

                if (frameNumber == 0){

                    categoria = "ENTRETENIMIENTO";

                }else if (frameNumber == 1){

                    categoria = "GEOGRAFÍA";

                }else if (frameNumber == 2){

                    categoria = "HISTORIA";

                }else if (frameNumber == 3){

                    categoria = "DEPORTES";
                }

            }

        }

        mp.stop();
        sonido.start();

        MyDialog dialog = new MyDialog();
        dialog.show(getFragmentManager(),"my_dialog");
        dialog.setCancelable(false);

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
                Intent intent = new Intent(MenuActivity.this,MainActivity.class);
                intent.putExtra("Calificacion",String.valueOf(calificacion));
                intent.putExtra("Acertadas",String.valueOf(acertadas));
                intent.putExtra("Contador",String.valueOf(contador));
                startActivity(intent);
                finish();
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


}

