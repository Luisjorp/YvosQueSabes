package Clases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Luis José on 19/04/2016.
 */
public class DBPreguntas extends SQLiteOpenHelper {

    private static final String DB_NAME = "examenDB";
    private static final int SCHEME_VERSION = 1;
    private SQLiteDatabase db;

    public DBPreguntas(Context context) {
        super(context, DB_NAME, null, SCHEME_VERSION);
        db = this.getWritableDatabase();
    }


    private ContentValues generarValores(EPreguntas preguntas){

        ContentValues valores = new ContentValues();
        valores.put(EPreguntas.FIELD_PREGUNTA,preguntas.getPregunta());
        valores.put(EPreguntas.FIELD_CORRECTA,preguntas.getCorrecta());
        valores.put(EPreguntas.FIELD_OPC1,preguntas.getOpcion1());
        valores.put(EPreguntas.FIELD_OPC2,preguntas.getOpcion2());
        valores.put(EPreguntas.FIELD_OPC3,preguntas.getOpcion3());
        valores.put(EPreguntas.FIELD_ESTADO,preguntas.getEstado());
        return valores;

    }

    public void InsertarPreguntas(EPreguntas preguntas){

        db.insert(EPreguntas.TABLE_NAME,null,generarValores(preguntas));

    }

    public ArrayList<EPreguntas> getPregunta(String valor){

        ArrayList<EPreguntas> preguntas = new ArrayList<>();
        String columnas[] = {EPreguntas.FIELD_ID, EPreguntas.FIELD_PREGUNTA, EPreguntas.FIELD_CORRECTA,
                            EPreguntas.FIELD_OPC1, EPreguntas.FIELD_OPC2, EPreguntas.FIELD_OPC3, EPreguntas.FIELD_ESTADO};

        Cursor c = db.query(EPreguntas.TABLE_NAME, columnas, EPreguntas.FIELD_ESTADO + "=?", new String[] {valor}, null, null, null);

        if (c.moveToFirst()){

            do {

                EPreguntas p = new EPreguntas();
                p.setId(c.getInt(0));
                p.setPregunta(c.getString(1));
                p.setCorrecta(c.getString(2));
                p.setOpcion1(c.getString(3));
                p.setOpcion2(c.getString(4));
                p.setOpcion3(c.getString(5));
                p.setEstado(c.getString(6));
                preguntas.add(p);

            }while(c.moveToNext());
        }

        return preguntas;
    }


    private ContentValues generarValoresHistorial(EHistorial historial){

        ContentValues valores = new ContentValues();
        valores.put(EHistorial.FIELD_FECHA,historial.getFecha());
        valores.put(EHistorial.FIELD_HORA,historial.getHora());
        valores.put(EHistorial.FIELD_CALIFICACION,historial.getCalificacion());
        return valores;

    }

    public void InsertarRegistro(EHistorial historial){

        db.insert(EHistorial.TABLE_NAME,null,generarValoresHistorial(historial));

    }

    public Cursor getHistorial(){

        String columnas[] = {EHistorial.FIELD_ID_HISTORIAL, EHistorial.FIELD_FECHA, EHistorial.FIELD_HORA,
                EHistorial.FIELD_CALIFICACION};

        Cursor c = db.query(EHistorial.TABLE_NAME, columnas,null, null, null, null, null);

        return c;
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(EPreguntas.CREATE_DB_TABLE);
        db.execSQL(EHistorial.CREATE_DB_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
