package com.example.ejercicio23;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.ejercicio23.transacciones.photograh;

public class SQLiteConexion  extends SQLiteOpenHelper {
    public SQLiteConexion(Context context, String dbname, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, dbname, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(photograh.CreateTableImagenes);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(photograh.DropeTableImagenes);
        onCreate(db);
    }

    public Cursor getAll() {
        return(getReadableDatabase().rawQuery("SELECT * FROM imagenes",null));
    }
    public void insert(byte[] bytes, String descripcion)
    {
        ContentValues cv = new ContentValues();

        cv.put(photograh.blobImagen,bytes);
        cv.put(photograh.descripcion,descripcion);
        Log.e("inserted", "inserted");
        getWritableDatabase().insert(photograh.tablaImagenes, photograh.idImagen,cv);

    }
    public byte[] getImage(Cursor c)
    {
        return(c.getBlob(1));
    }

}
