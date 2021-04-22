package com.df.formulariobd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ciudades.db";

    public MyDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table ciudad (id INTEGER PRIMARY KEY, nombre TEXT, poblacion TEXT, latitud TEXT, longitud TEXT) " );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists ciudad");
        onCreate(db);
    }

    public void insertArea(SQLiteDatabase db, Ciudad ciudad){
        ContentValues values = new ContentValues();
        values.put("id", ciudad.getId());
        values.put("nombre", ciudad.getNombre());
        values.put("poblacion", ciudad.getPoblacion());
        values.put("latitud", ciudad.getLatitud());
        values.put("longitud", ciudad.getLongitud());
        db.insert("ciudad", null, values);
    }

    public ArrayList<Ciudad> selectCiudad(SQLiteDatabase db) {
        ArrayList<Ciudad> ciudades = new ArrayList<>();
        Cursor filas = db.rawQuery("Select * from ciudad", null);
        if (filas.moveToFirst()) {
            do {
                Ciudad ciudad = new Ciudad(filas.getInt(0),
                        filas.getString(1),
                        filas.getString(2),
                        filas.getString(3),
                        filas.getString(4));
                ciudades.add(ciudad);
            } while (filas.moveToNext());
        }
        return ciudades;
    }
}

