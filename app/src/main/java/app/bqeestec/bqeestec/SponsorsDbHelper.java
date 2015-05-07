package app.bqeestec.bqeestec;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SponsorsDbHelper extends SQLiteOpenHelper {

    private static String DATABASE_PATH = "data/data/net.eestec.madridcongress2015/databases/";
    public static final String DATABASE_NAME = "Sponsors.db";
    public static final int DATABASE_VERSION = 1;

    private final Context sponsorsDbContext;

    public SponsorsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        sponsorsDbContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SponsorsDbContract.CREATE_SPONSORS_SCRIPT);
        sqLiteDatabase.execSQL(SponsorsDbContract.INSERT_SPONSORS_SCRIPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }
}
