package yvqs.app.novatek.com.yvosquesabes;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

import Clases.DBPreguntas;
import Clases.EPreguntas;

public class Splash_Screen extends AppCompatActivity {

    private boolean estado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);
        CargarPreferencias();
        if(estado){

            Intent intent = new Intent(Splash_Screen.this,MainActivity.class);
            startActivity(intent);
            finish();

        }else{

            ProcesoCarga proceso = new ProcesoCarga();
            proceso.execute();
        }

    }

    private void CargarPreferencias(){

        SharedPreferences misPreferencias = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        estado = misPreferencias.getBoolean("isLoad", false);
    }

    private void GuardarPreferencias(boolean valor){

        SharedPreferences misPreferencias = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = misPreferencias.edit();
        editor.putBoolean("isLoad", valor);
        editor.commit();

    }

    private class ProcesoCarga extends AsyncTask<Void,Void,Void>{

        ProgressDialog dialog;
        ArrayList<EPreguntas> preguntass = new ArrayList<EPreguntas>(Arrays.asList(
        //**********************************************************DEPORTES*******************************************************************************************
                new EPreguntas("¿Cuál es el equipo que utiliza lunas en su escudo?","Xelajú M.C.","Municipal","Comunicaciones","Petapa","DEPORTES"),
                new EPreguntas("¿En qué año Erick Barrondo ganó la medalla de plata en Juegos Olímpicos?","2012","2008","2006","2010","DEPORTES"),
                new EPreguntas("¿Referencia con la que se le llama a la afición de Municipal?","Los Rojos","Diablos","Furia Roja","Rojipasionales","DEPORTES"),
                new EPreguntas("¿Con cuántos deportistas asistió Guatemala a los Juegos Olímpicos de 1988?","30","25","3","12","DEPORTES"),
                new EPreguntas("¿Nombre del estadio oficial de la Selección de Guatemala?","Mateo Flores","Tecún Umán","Rigoberta Menchú","La Bombonera","DEPORTES"),
                new EPreguntas("¿En qué año empezó a participar Guatemala en los Juegos Olímpicos","1952","1960","1958","1953","DEPORTES"),
                new EPreguntas("¿Equipo con más titulos en el Campeonato de Liga Guatemalteca?","Comunicaciones","Municipal","Aurora","Xelajú M.C.","DEPORTES"),
                new EPreguntas("¿Cuántos títulos tiene Xelajú M.C.?","5","4","3","6","DEPORTES"),
                new EPreguntas("¿Nombre del Goleador Histórico más grande de Guatemala?","Juan Carlos Plata","Israel Silva","Mario Acevedo","Carlos Ruiz","DEPORTES"),
                new EPreguntas("¿Equipo que ha logrado vencer todas las finales que disputó?","Deportivo Jalapa","Antigua GFC","Cobán Imperial","Municipal","DEPORTES"),
                new EPreguntas("¿Año en que se crea la Federación de Atletismo en Guatemala?","1945","1948","1950","1952","DEPORTES"),
                new EPreguntas("¿Máximo exponente de salto alto en Guatemala?","Teodoro Palacios Flores","Juan Ramón Calderón","Geovani López","Mario Raúl García","DEPORTES"),
                new EPreguntas("¿Cuántas medallas de oro se han ganado en los Juegos Centroamericanos (Salto Alto)?","3","6","4","1","DEPORTES"),
                new EPreguntas("¿En que año la Selección de Ajedrez de Guatemala gana el primer lugar en el Campeonato Centroamericano?","2013","2002","1999","2012","DEPORTES"),

        //**********************************************************HISTORIA*******************************************************************************************
                new EPreguntas("¿Quién colonizó Guatemala?","Pedro de Alvarado","Cristobal Colón","Raúl García","Justo Rufino Barrios","HISTORIA"),
                new EPreguntas("¿Quién fue el primer presidente de Guatemala?","Gabino Gaínza","Pedro Molina Mazariegos","Mariano Gálvez","Mariano Paredes","HISTORIA"),
                new EPreguntas("¿Quién aparece en el billete de Q50.00?","Carlos Zachirsson","Justo Rufino Barrios","Miguel García Granados","Francisco Marroquín","HISTORIA"),
                new EPreguntas("¿En qué fecha se firmaron los acuerdos de paz en Guatemala?","29 de Diciembre de 1996","29 de Noviembre de 1996","29 de Diciembre de 1997","29 de Noviembre de 1997","HISTORIA"),
                new EPreguntas("¿En qué año dio inicio el conflicto armado interno de Guatemala?","1960","1988","1969","1952","HISTORIA"),
                new EPreguntas("¿Qué presidente declaró estado independiente a Belice?","Jorge Serrano Elías","Marco Vinicio Cerezo","Ramiro de León Carpio","Mariano Paredes","HISTORIA"),
                new EPreguntas("¿En qué fecha fue reconocido el sexto estado de los altos?","5 de Junio de 1838","2 de Febrero de 1838","5 de Junio de 1839","2 de Febrero de 1839","HISTORIA"),
                new EPreguntas("¿Quién escribió la letra del himno nacional de Guatemala?","José Joaquín Palma","Rafael Álvarez Ovalle","Efraín Recinos","Rafael García Goyena","HISTORIA"),
                new EPreguntas("¿En qué fecha nació el reconocido pintor Efraín Recinos?","15 de Mayo de 1928","2 de Octubre de 1926","15 de Marzo de 1928","2 de Junio de 1926","HISTORIA"),
                new EPreguntas("¿A qué se dedicaba Miguel Ángel Asturias?","Escritor","Poeta","Futbolista","Comerciante","HISTORIA"),
                new EPreguntas("¿En qué fecha fue firmada el acta independencia de Guatemala?","15 de Septiembre de 1821","16 de Septiembre de 1812","15 de Septiembre de 1822","11 de Septiembre de 1815","HISTORIA"),
                new EPreguntas("¿En qué año fue compuesta la música del himno nacional?","1897","1905","1855","1907","HISTORIA"),
                new EPreguntas("¿En qué fecha comenzó la circulación del billete de Q200.00?","23 de Agosto de 2010","23 de Febrero de 2010","05 de Agosto de 2008","11 de Marzo de 2009","HISTORIA"),
                new EPreguntas("¿Quién fue el presidente acusado de lavado de dinero?","Alfonso Portillo","Otto Pérez Molina","Efraín Ríos Montt","Alvaro Colom","HISTORIA"),
                new EPreguntas("¿En qué fecha fue adoptado el escudo nacional de Guatemala?","18 de Noviembre de 1871","18 de Noviembre de 1971","18 de Septiembre de 1821","18 de Septiembre de 1871","HISTORIA"),
                new EPreguntas("¿Qué personaje ilustraba el billete de cincuenta centavos?","Tecún Uman","Rigoberta Menchú","Miguel Ángel Asturias","Justo Rufino Barrios","HISTORIA"),
                new EPreguntas("¿En qué año ganó el premio Nobel de literatura Miguel Ángel Asturias?","1967","1965","1855","1907","HISTORIA"),
                new EPreguntas("¿En qué fecha fue fundada la Universidad de San Carlos de Guatemala?","31 de Enero de 1676","31 de Enero de 1767","28 de Enero de 1676","28 de Febrero de 1686","HISTORIA"),
                new EPreguntas("¿En qué año se fundó BANGUAT?","1945","1935","1925","1994","HISTORIA"),
                new EPreguntas("¿En qué año fue el terremoto de San Marcos?","2012","2004","2010","2014","HISTORIA"),
                new EPreguntas("¿En qué año se eligió a un presidente de manera democrática por primera vez en Guatemala?","1945","1944","1935","1987","HISTORIA"),
                new EPreguntas("¿En qué año se dio la revolución en Guatemala?","1944","1945","1936","1940","HISTORIA"),

        //**********************************************************ENTRETENIMIENTO*******************************************************************************************
                new EPreguntas("¿Canción de Ricardo Arjona con Gaby Moreno?","Fuiste Tú","Historia de un Taxi","Mujeres","Adiós","ENTRETENIMIENTO"),
                new EPreguntas("Película guatemalteca que regresa a las carteleras de Panajachel...","Ixcanul","Yo quiero ser Presidente","El Misterio de la Casa","Aquí me quedo","ENTRETENIMIENTO"),
                new EPreguntas("¿Edgar Figueroa es presidente y director general de qué compañía?","Wi-Fi Alliance","Duolingo","Apple","Adobe","ENTRETENIMIENTO"),
                new EPreguntas("¿Cómo se llama el libro del cineasta Jayro Bustamante?","Cuando sea grande","Mañana es para siempre","Conmigo no basta","Hasta mañana","ENTRETENIMIENTO"),
                new EPreguntas("¿En qué año nació el actor Oscar Isaac Hernández?","1980","1982","1985","1990","ENTRETENIMIENTO"),
                new EPreguntas("¿Nombre de la cineasta que tituló a su primer trabajo como 'The List'?","Daniela Arguello","Sofia García","Marian de León","Marylin Proceres","ENTRETENIMIENTO"),
                new EPreguntas("¿Cantautor de Origen Quetzalteco destacado en el género folk de la música?","Tuco Cárdenas","Martín Godoy","Pedro Pérez","Don Chamu","ENTRETENIMIENTO"),
                new EPreguntas("¿Cuál de los siguientes géneros no es relacionado con Malacates Trebol Shop?","Cumbia","Reggae","Ska","Rock Latino","ENTRETENIMIENTO"),
                new EPreguntas("¿Grupo que en el año 2007 fue seleccionado como Mejor Grupo Nacional de Guatemala en España?","Malacates Trebol Shop","Viento En Contra","Viernes Verde","El Clubo","ENTRETENIMIENTO"),
                new EPreguntas("¿A qué se dedica Tavo Bárcenas?","Cantante","Actor","Cineasta","Productor","ENTRETENIMIENTO"),
                new EPreguntas("¿Cantante que en el año 2004 participó en el primer Reality Show de Guatemala?","Tavo Bárcenas","Francisco Paez","Ricardo Arjona","El Gordo","ENTRETENIMIENTO"),
                new EPreguntas("¿Youtuber guatemalteco reconocido por sus 'doblajes al buen chapín'??","Ronald Mackay","Chema007","Duck You!","Luisjo dotcom","ENTRETENIMIENTO"),
                new EPreguntas("¿Artista nacional que interpreta el tema musical principal de la película guatemalteca 'Aquí me quedo'? ?","El Gordo","Tuco Cárdenas","Ricardo Arjona","Carlos Peña","ENTRETENIMIENTO"),
                new EPreguntas("¿Expresión utilizada por chapines para demostrar asombro?","¡Que chilero!","¡Que padre!","¡Tuanis!","¡Orale!","ENTRETENIMIENTO"),
                new EPreguntas("¿Niña guatemalteca ganadora de 'La Academia Kids 2014'?","Karla Herrarte","Fabiola Rodas","Gaby Moreno","Flamina","ENTRETENIMIENTO"),

        //**********************************************************GEOGRAFIA*******************************************************************************************
                new EPreguntas("¿Qué país no hace frontera con Guatemala?","Costa Rica","Belice","México","El Salvador","GEOGRAFÍA"),
                new EPreguntas("¿Cuál es el volcán más alto de Guatemala?","Tajumulco","Tolimán","Pacaya","Santa María","GEOGRAFÍA"),
                new EPreguntas("¿Cuál es el río más extenso de Guatemala?","Motagua","Usumacinta","Polochic","Chixoy","GEOGRAFÍA"),
                new EPreguntas("¿Cuál es la cabecera de Petén?","Flores","Poptún","La Libertad","Las Cruces","GEOGRAFÍA"),
                new EPreguntas("¿En qué departamento están ubicadas las grutas de Lanquin?","Alta Verapaz","Baja Verapaz","Jutiapa","Jalapa","GEOGRAFÍA"),
                new EPreguntas("¿Cuál es el lago más grande de Guatemala?","Lago de Izabal","Lago Atitlán","Lago de Amatitlán ","Lago Petén Itzá","GEOGRAFÍA"),
                new EPreguntas("¿En qué departamento se encuentra el municipio de Momostenango?","Totonicapán","San Marcos","Izabal","Sololá","GEOGRAFÍA"),
                new EPreguntas("¿Cuál es la cabecera del departamento de Quetzaltenango?","Quetzaltenango","Coatepeque","Salcajá","La Esperanza","GEOGRAFÍA"),
                new EPreguntas("¿Cuántos volcanes tiene Guatemala?","37","42","35","41","GEOGRAFÍA"),
                new EPreguntas("¿En qué departamento se encuentra el volcán Santa María?","Quetzaltenango","Ciudad de Guatemala","San Marcos","Zacapa","GEOGRAFÍA"),
                new EPreguntas("¿Qué departamento tiene la mayor población?","Ciudad de Guatemala","Santa Rosa","Retalhuleu","Huehuetenango","GEOGRAFÍA"),
                new EPreguntas("¿En qué departamento se encuentra Esquipulas?","Chiquimula","El Quiché","Escuintla","Suchitepéquez","GEOGRAFÍA"),
                new EPreguntas("¿En dónde se encuentra el templo maya 'El Gran Jaguar'?","Petén","Chimaltenango","Zacapa","Huehuetenango","GEOGRAFÍA"),
                new EPreguntas("¿Quién descubrió el sitio arqueológico 'Quirigua'?","John L. Stephens","Mariano Rivera Paz","Carlos Salazar Castro","César Brañas","GEOGRAFÍA"),
                new EPreguntas("¿En qué departamento está el municipio 'San Martín Zapotitlan'?","Retalhuleu","Escuintla","Suchitepéquez","Sacatepéquez","GEOGRAFÍA"),
                new EPreguntas("¿Qué es Yaxhá?","Sitio Arqueológico","Municipio","Departamento","Aldea","GEOGRAFÍA"),
                new EPreguntas("¿Cuál es el departamento con menor altitud sobre el nivel del mar?","Izabal","Petén","Zacapa","Suchitepéquez","GEOGRAFÍA"),
                new EPreguntas("¿En dónde desemboca el río Usumacinta?","Golfo de México","Golfo de Honduras","Océano Pacífico","Océano Atlántico","GEOGRAFÍA"),
                new EPreguntas("¿En qué meses es invierno en Guatemala?","Mayo – Octubre","Noviembre – Abril","Enero – Junio","Julio – Diciembre","GEOGRAFÍA"),
                new EPreguntas("¿En qué meses es verano en Guatemala?","Noviembre – Abril","Mayo – Octubre","Julio – Diciembre","Enero – Junio","GEOGRAFÍA"),
                new EPreguntas("¿En que departamento está el volcán Santiaguito?","Quetzaltenango","Huehuetenango","Suchitepequez","Chimaltenango","GEOGRAFÍA")
        ));
        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(Splash_Screen.this);
            dialog.setTitle("Iniciando app");
            dialog.setMessage("Cargando...");
            dialog.show();
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            GuardarPreferencias(true);
            if(dialog.isShowing()){

                dialog.dismiss();
                Intent intent = new Intent(Splash_Screen.this,MainActivity.class);
                startActivity(intent);
                finish();

            }
        }

        @Override
        protected Void doInBackground(Void... params) {

            DBPreguntas helper = new DBPreguntas(Splash_Screen.this);

            for (int i=0; i<preguntass.size();i++){

                EPreguntas preg = new EPreguntas();
                preg = preguntass.get(i);

                helper.InsertarPreguntas(preg);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            return null;
        }
    }




}
