package jazuli.com.realm.belajarSQL.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.R.attr.version;

/**
 * Created by jazuli on 11/02/17.
 */

public class My extends SQLiteOpenHelper{

    private  static final int  version = 1;
    private static final String db_name = "Santri";

    public static String TABLE_SANTRI = "table_santri";
    public static String ID_SANTRI= "id_santri";
    public static String NAMA_SANTRI= "nama_santri";
    public static String ASAL_KOTA= "jakarta";
    public static String SKILL = "skill";


    public My(Context context) {
        super(context,db_name, null, version );
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "
                + TABLE_SANTRI + "("
                + ID_SANTRI + " INTEGER PRIMARY KEY,"
                + NAMA_SANTRI + " TEXT,"
                + ASAL_KOTA + " TEXT,"
                + SKILL + " TEXT )" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST " + TABLE_SANTRI);
    }
    //create data santri
    public boolean createDataSantri(String id,
                                    String nama_santri,
                                    String asal_kota,
                                    String skill){
    SQLiteDatabase database = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_SANTRI, id);
        contentValues.put(NAMA_SANTRI, nama_santri);
        contentValues.put(ASAL_KOTA, asal_kota);
        contentValues.put(SKILL, skill);

        long result = database.insert(TABLE_SANTRI, null, contentValues);
        return result != -1 ;
    }
    //read data santri
    public Cursor readAllDataSantri(){
        SQLiteDatabase database = this.getWritableDatabase();

        //cara untuk memanggil kolom dari database bisa seperti ini
//        Cursor cursor = database.rawQuery("select " + ID_SANTRI +","
//                +NAMA_SANTRI+", "
//                +ASAL_KOTA+", "
//                +SKILL+" from "+TABLE_SANTRI,null);


        //maupun seperti ini * = data semua colom
    Cursor cursor = database.rawQuery("select * from "+TABLE_SANTRI,null);


    //    cursor.close();
        return cursor;
    }
    public int updateNameSantri(String id, String nama_baru){
        SQLiteDatabase database = this.getWritableDatabase();
        //prepare data value
        ContentValues contentValues = new ContentValues();
        //put data value
        contentValues.put(NAMA_SANTRI, nama_baru);
        //updating row
        return database.update(TABLE_SANTRI,contentValues,ID_SANTRI + " = ?",
                new String[]{id});
    }
    //dalete data santri
    public void deleteAllDataSantri(String id){
        SQLiteDatabase database = this.getWritableDatabase();

        database.delete(TABLE_SANTRI, ID_SANTRI + " = ?", new String[]{id});
        database.close();
    }
}