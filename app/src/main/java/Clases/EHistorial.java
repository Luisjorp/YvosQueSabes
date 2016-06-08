package Clases;

/**
 * Created by Luis José on 19/04/2016.
 */
public class EHistorial {
    public static final String TABLE_NAME = "tblHistorial";
    public static final String FIELD_ID_HISTORIAL = "_id";
    public static final String FIELD_FECHA = "fecha";
    public static final String FIELD_HORA = "hora";
    public static final String FIELD_CALIFICACION = "calificacion";

    public static final String CREATE_DB_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " +
                                                 FIELD_ID_HISTORIAL + " integer primary key autoincrement," +
                                                 FIELD_FECHA + " text," +
                                                 FIELD_HORA + " text," +
                                                 FIELD_CALIFICACION + " text" + ");";

    private int id;
    private String fecha;
    private String hora;
    private String calificacion;


    public EHistorial() {
        this.fecha = fecha;
    }

    public EHistorial(String fecha, String hora, String calificacion){
        this.fecha = fecha;
        this.hora = hora;
        this.calificacion = calificacion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
