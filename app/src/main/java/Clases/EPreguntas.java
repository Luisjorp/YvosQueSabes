package Clases;

/**
 * Created by Luis José on 19/04/2016.
 */
public class EPreguntas {
    public static final String TABLE_NAME = "tblPreguntas";
    public static final String FIELD_ID = "_id";
    public static final String FIELD_PREGUNTA = "pregunta";
    public static final String FIELD_CORRECTA = "correcta";
    public static final String FIELD_OPC1 = "opcion1";
    public static final String FIELD_OPC2 = "opcion2";
    public static final String FIELD_OPC3 = "opcion3";
    public static final String FIELD_ESTADO = "estado";

    public static final String CREATE_DB_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " +
                                                 FIELD_ID + " integer primary key autoincrement," +
                                                 FIELD_PREGUNTA + " text," +
                                                 FIELD_CORRECTA + " text," +
                                                 FIELD_OPC1 + " text," +
                                                 FIELD_OPC2 + " text," +
                                                 FIELD_OPC3 + " text," +
                                                 FIELD_ESTADO + " integer default 0" + ");";

    private int id;
    private String pregunta;
    private String correcta;
    private String opcion1;
    private String opcion2;
    private String opcion3;
    private String estado;


    public EPreguntas() {
        this.pregunta = pregunta;
    }

    public EPreguntas(String pregunta,String correcta, String op1, String op2, String op3, String estado){
        this.pregunta = pregunta;
        this.correcta = correcta;
        this.opcion1 = op1;
        this.opcion2 = op2;
        this.opcion3 = op3;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getCorrecta() {
        return correcta;
    }

    public void setCorrecta(String correcta) {
        this.correcta = correcta;
    }

    public String getOpcion1() {
        return opcion1;
    }

    public void setOpcion1(String opcion1) {
        this.opcion1 = opcion1;
    }

    public String getOpcion2() {
        return opcion2;
    }

    public void setOpcion2(String opcion2) {
        this.opcion2 = opcion2;
    }

    public String getOpcion3() {
        return opcion3;
    }

    public void setOpcion3(String opcion3) {
        this.opcion3 = opcion3;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
