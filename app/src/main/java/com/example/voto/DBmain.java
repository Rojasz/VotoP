package com.example.voto;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBmain extends SQLiteOpenHelper {
    public static final String DB = "voto.db";
    public static final int VER = 1;
    public DBmain(@Nullable Context context) {
        super(context, DB, null, VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table tablavotos(id integer primary key, name text)";
        db.execSQL(query);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String query = "drop table if exists tablavotos";
        db.execSQL(query);
        onCreate(db);

    }
}














