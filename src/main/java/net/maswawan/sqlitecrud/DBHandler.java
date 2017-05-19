package net.maswawan.sqlitecrud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wawan on 5/17/2017.
 */
public class DBHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "db_mahasiswa";
    private static int DATABASE_VERSION = 1;
    private static final String tb_mahasiswa = "tb_mahasiswa";
    private static final String tb_mhs_id = "id";
    private static final String tb_mhs_nama = "nama";
    private static final String tb_mhs_kelas = "kelas";

    private static final String CREATE_TABLE_MAHASISWA = "CREATE TABLE "
            + tb_mahasiswa + "("
            + tb_mhs_id + " INTEGER PRIMARY KEY, "
            + tb_mhs_nama + " TEXT, "
            + tb_mhs_kelas + " TEXT "
            + ")";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MAHASISWA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void CreateMahasiswa(ModalMahasiswa mdNotif){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(tb_mhs_id,mdNotif.get_id());
        values.put(tb_mhs_nama,mdNotif.get_nama());
        values.put(tb_mhs_kelas,mdNotif.get_kelas());
        db.insert(tb_mahasiswa,null,values);
        db.close();
    }

    public List<ModalMahasiswa> ReadMahasiswa(){
        List<ModalMahasiswa> mhsList = new ArrayList<>();
        String query = "SELECT * FROM " + tb_mahasiswa;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do{
                ModalMahasiswa mhs = new ModalMahasiswa();
                mhs.set_id(cursor.getString(0));
                mhs.set_nama(cursor.getString(1));
                mhs.set_kelas(cursor.getString(2));
                mhsList.add(mhs);
            }while (cursor.moveToNext());

        }
        db.close();
        return mhsList;
    }

    public int UpdateMahasiswa(ModalMahasiswa mdNotif){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(tb_mhs_nama,mdNotif.get_nama());
        values.put(tb_mhs_kelas,mdNotif.get_kelas());
        return db.update(tb_mahasiswa,values,tb_mhs_id + "= ?",new String[]{String.valueOf(mdNotif.get_id())});

    }

    public void DeleteMahasiswa(ModalMahasiswa mdNotif){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tb_mahasiswa,tb_mhs_id + "= ?",new String[]{String.valueOf(mdNotif.get_id())});
        db.close();
    }
}
