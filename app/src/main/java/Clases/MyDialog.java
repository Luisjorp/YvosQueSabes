package Clases;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

import yvqs.app.novatek.com.yvosquesabes.ExamenActivity;
import yvqs.app.novatek.com.yvosquesabes.MenuActivity;
import yvqs.app.novatek.com.yvosquesabes.R;

public class MyDialog extends DialogFragment {

    LayoutInflater inflater;
    View v;
    TextView categoria;
    int calificacion,contador,acertadas;
    private String categoriav;



    @TargetApi(Build.VERSION_CODES.M)
    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Bundle bundle = getActivity().getIntent().getExtras();
        if(bundle!= null){
            contador = Integer.valueOf(bundle.getString("Contador"));
            calificacion = Integer.valueOf(bundle.getString("Calificacion"));
            acertadas = Integer.valueOf(bundle.getString("Acertadas"));
        }

        inflater = getActivity().getLayoutInflater();
        v = inflater.inflate(R.layout.my_dialog_background, null);
        categoria = (TextView) v.findViewById(R.id.lblCat);

        MenuActivity actividad = new MenuActivity();
        categoriav = actividad.categoria;

        switch (categoriav) {
            case "DEPORTES":
                categoria.setTextColor(getResources().getColor(R.color.icons));
                categoria.setBackground(getResources().getDrawable(R.drawable.deporbann2));
                break;
            case "GEOGRAFÍA":
                categoria.setTextColor(getResources().getColor(R.color.icons));
                categoria.setBackground(getResources().getDrawable(R.drawable.deporbann));
                break;
            case "HISTORIA":
                categoria.setTextColor(getResources().getColor(R.color.primary_text));
                categoria.setBackground(getResources().getDrawable(R.drawable.histobann));
                break;
            case "ENTRETENIMIENTO":
                categoria.setTextColor(getResources().getColor(R.color.icons));
                categoria.setBackground(getResources().getDrawable(R.drawable.enterbann));
            break;
        }

        categoria.setText(categoriav);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(v).setPositiveButton("¡DALE PUES!", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getActivity(), ExamenActivity.class);
                intent.putExtra("Contador", String.valueOf(contador));
                intent.putExtra("Categoria", String.valueOf(categoriav));
                intent.putExtra("Acertadas", String.valueOf(acertadas));
                intent.putExtra("Calificacion", String.valueOf(calificacion));
                startActivity(intent);
            }
        });
        builder.setCancelable(false);
        return builder.create();
    }

}
