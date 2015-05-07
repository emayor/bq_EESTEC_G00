package app.bqeestec.bqeestec;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.io.IOException;

public class SponsorsDbContract {

    //Metainformación de la base de datos
    public static final String SPONSORS_TABLE_NAME = "Sponsors";
    public static final String STRING_TYPE = "text";
    public static final String INT_TYPE = "integer";

    //Campos de la tabla
    public static class ColumnSponsors {
        public static final String ID_SPONSORS = BaseColumns._ID;
        public static final String NAME_SPONSORS = "name";
        public static final String LOGO_SPONSORS = "logo";
        public static final String INFO_SPONSORS = "info";
        public static final String CONTACT_SPONSORS = "contact";
    }

    //Script de Creación de la tabla Quotes
    public static final String CREATE_SPONSORS_SCRIPT =
            "create table " + SPONSORS_TABLE_NAME + "(" +
                    ColumnSponsors.ID_SPONSORS + " " + INT_TYPE + " primary key autoincrement," +
                    ColumnSponsors.NAME_SPONSORS + " " + STRING_TYPE + " not null," +
                    ColumnSponsors.LOGO_SPONSORS + " " + INT_TYPE + " not null," +
                    ColumnSponsors.INFO_SPONSORS + " " + STRING_TYPE + " not null," +
                    ColumnSponsors.CONTACT_SPONSORS + " " + STRING_TYPE + " not null)";

    //Scipssts de inserción por defecto
    public static final String INSERT_SPONSORS_SCRIPT =
            "insert into " + SPONSORS_TABLE_NAME + " values" +
                    "(null," + "\"UPM\", " + "\"upm\"" + ", \"Universidad Politécnica de Madrid (Technical University of Madrid). The University was founded in 1971 by joining together several centenary centres, which already existed. It is mainly situated in the University campus, closed to the city centre, and called Ciudad Universitaria. It counts with almost 40,000 students and over 3,000 teachers and various researchers from different fields of Engineering and Architecture. It also has many investigating facilities. It is considered one of the best technical Universities of Spain.\"," + "\"www.upm.es\")," +
                    "(null," + "\"ETSIT\", " + "\"logoetsit\"" + ", \"Superior Technical School of Telecommunication’s Engineers. Founded in 1957, it joined to the UPM in 1971. It instructs the title of Telecommunication Engineering since 1965, and the Biomedical engineering since 2011. With a great input of R&D, among its main achievements we find the construction of the first LASER in Spain (1966), the development of the first direct TV receptor through satellite (1982), the sending of the first e-mail with the opened standards of the @ (1985) or the world record of energetic efficiency for a photovoltaic cell (2008).\"," + "\"www.etsit.upm.es\")," +
                    "(null," + "\"Fundetel\", " + "\"fundetel\"" + ", \"Institution linked to the ETSIT-UPM, which has as its aim the realisation, promotion, support and management of the teaching activities, technological innovation and cultural extension typical of the University in the Information and Communication Technologies field (ICT). It supports the ETSIT on its relations with the ICT sphere, and promotes the collaboration between teachers, students and the professionals of the field.\"," + "\"www.fundetel.etsit.upm.es\")," +
                    "(null," + "\"BQ\", " + "\"bq\"" + ", \"Spanish business founded in 2010 by 6 students from ETSIT-UPM, dedicated to design, sale and distribution of electronic readers, tablets, smartphones, 3D printers and robotics kits. It also has a great inversion in education by means of the teaching spheres thanks to a scholarships program, through which it encourages the incorporation of students from the UPM to technological projects, allowing them a direct contact with the corporation. It collaborates in various R&D projects with diverse universities. \"," + "\"www.bq.com\")," +
                    "(null," + "\"Telefónica I+D\", " + "\"telefonicaid\"" + ", \"It was founded in 1988, and it is the enterprise part of the Telefónica Group that is dedicated to innovation and development, and first business in Europe in many investigation projects and it cooperates with over 150 universities worldwide. In addition, the staff is made up by a 97% of university students from 18 different nationalities. Amongst its main achievements, we find the great management systems of fixed and mobile networks (1996), data commutators (1991), the Internet access services (1996), the development for the household and the connected automobile (2000), Telefónica’s pre-paid mobile system (2004), or the interactive digital television (Imagenio) (2004).\"," + "\"www.tid.es\")";

    //Variables para la manipulación de datos
    private SponsorsDbHelper openHelperSponsors;
    private SQLiteDatabase databaseSponsors;

    public SponsorsDbContract(Context context) {
        //Creando una instancia hacia la base de datos
        openHelperSponsors = new SponsorsDbHelper(context);
        databaseSponsors = openHelperSponsors.getWritableDatabase();
    }

    public void saveSponsorRow(String name, int idLogo, String info, String contact) {
        //Contenedor de valores
        ContentValues values = new ContentValues();

        //Seteando valores
        values.put(ColumnSponsors.NAME_SPONSORS, name);
        values.put(ColumnSponsors.LOGO_SPONSORS, idLogo);
        values.put(ColumnSponsors.INFO_SPONSORS, info);
        values.put(ColumnSponsors.CONTACT_SPONSORS, contact);

        //Insertando en la base de datos
        databaseSponsors.insert(SPONSORS_TABLE_NAME, null, values);
    }

    public Cursor getAllSponsors() {
        //Seleccionamos todas la filas de la tabla
        return databaseSponsors.rawQuery("select * from " + SPONSORS_TABLE_NAME, null);
    }

    public Cursor getSponsorByName(String name) {
        String selection = ColumnSponsors.NAME_SPONSORS + " = ? ";
        String selectionArgs[] = new String[]{name};
        return databaseSponsors.query(
                SPONSORS_TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
    }

}
