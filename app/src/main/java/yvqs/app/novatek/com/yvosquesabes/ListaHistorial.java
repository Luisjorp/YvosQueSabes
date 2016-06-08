package yvqs.app.novatek.com.yvosquesabes;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import Clases.DBPreguntas;

public class ListaHistorial extends AppCompatActivity {

    DBPreguntas db;
    ListView lista;
    List<String> registro = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_historial);

        lista = (ListView) findViewById(R.id.listaHistorial);
        View header = (View) getLayoutInflater().inflate(R.layout.historial_header,null);
        lista.addHeaderView(header);

        showRegistros();


    }

    private void showRegistros() {

       db = new DBPreguntas(this);
        Cursor c = db.getHistorial();

        registro = new ArrayList<String>();
        String fecha = "", hora = "", calificacion = "";

        if (c.moveToFirst()){

            do {

                fecha = c.getString(1);
                hora = c.getString(2);
                calificacion = c.getString(3);
                registro.add(fecha + "\t\t\t\t" + hora + "\t\t\t\t\t\t" + calificacion + " puntos");

            }while (c.moveToNext());

        }

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, registro);
        lista.setAdapter(adaptador);

    }

}
