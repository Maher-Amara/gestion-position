package com.maherdev.gestionPosition;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class PositionContactHelper extends SQLiteOpenHelper {
    public static final String filename="mesposition.db";
    public static int version=1;
    public static final String table_position="PositionContact";
    public static final String col_id="Identifiant";
    public static final String col_numero="Numero";
    public static final String col_pseudo="Pseudo";
    public static final String col_latitude="Latitude";
    public static final String col_longitude="Longitude";
    public PositionContactHelper(@Nullable Context context,
                                 @Nullable String name,
                                 @Nullable SQLiteDatabase.CursorFactory factory,
                                 int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String req="Create table "+table_position+" (" +
                col_id +" integer primary key autoincrement," +
                col_numero+" Text not null," +
                col_pseudo+" Text not null," +
                col_longitude+" Text not null," +
                col_latitude+" Text not null )";
        db.execSQL(req);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table "+table_position);
        onCreate(db);
    }
}
