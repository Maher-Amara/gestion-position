package com.maherdev.gestionposition;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class PositionContactManager {
    SQLiteDatabase db;
    public  PositionContactManager(Context con){
        //PositionContactHelper.version++;
        PositionContactHelper helper=new PositionContactHelper(con,
                PositionContactHelper.filename,
                null,PositionContactHelper.version);
        db=helper.getWritableDatabase();
    }
    public long inserer(String num, String ps, String lon, String lat){
        ContentValues v= new ContentValues();
        v.put(PositionContactHelper.col_numero,num);
        v.put(PositionContactHelper.col_pseudo,ps);
        v.put(PositionContactHelper.col_longitude,lon);
        v.put(PositionContactHelper.col_latitude,lat);
        long a=db.insert(PositionContactHelper.table_position,null,v);
        return a;
    }
    public long supprimerSelonId (int id)
    {
        long a=0;
        a=db.delete(PositionContactHelper.table_position,
                PositionContactHelper.col_id+"="+id,
                null);
        return a;
    }
    public  ArrayList<PositionContact> getAllPosition()
    {
        ArrayList<PositionContact> data = new ArrayList<PositionContact>();
        Cursor cr = db.query(PositionContactHelper.table_position,new String []{
                        PositionContactHelper.col_id,
                        PositionContactHelper.col_numero,
                        PositionContactHelper.col_pseudo,
                        PositionContactHelper.col_longitude,
                        PositionContactHelper.col_latitude,

                },
                null,
                null,
                null,
                null,
                null);

        cr.moveToFirst();
        while(! cr.isAfterLast()) {
            int id = cr.getInt(0);
            String num = cr.getString(1);
            String ps = cr.getString(2);
            String lon = cr.getString(3);
            String lat = cr.getString(4);
            data.add(new PositionContact(id, num, ps, lon, lat));
            cr.moveToNext();
        }
        return data;
    }
}
