package com.maherdev.gestionPosition;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public abstract class Modifier extends AppCompatActivity  {
    EditText edlat_mod,edlong_mod,edpseudo_mod,ednum_mod;
    Button btnamod;
    int id;

    public void onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        LinearLayout root = (LinearLayout)inflater.inflate(R.layout.activity_modifier, container, false);
        btnamod = root.findViewById(R.id.btnmod);
        edlat_mod = root.findViewById(R.id.edlatitude_mod);
        edlong_mod = root.findViewById(R.id.edlongitude_mod);
        edpseudo_mod = root.findViewById(R.id.edpseudo_mod);
        ednum_mod = root.findViewById(R.id.ednumero_mod);
        id = getIntent().getExtras().getInt("id");
        btnamod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num = ednum_mod.getText().toString();
                String ps = edpseudo_mod.getText().toString();
                String lat = edlat_mod.getText().toString();
                String lon = edlong_mod.getText().toString();

                ContentValues c= new ContentValues();
                c.put(PositionContactHelper.col_numero,num);
                c.put(PositionContactHelper.col_pseudo,ps);
                c.put(PositionContactHelper.col_longitude,lon);
                c.put(PositionContactHelper.col_latitude,lat);
                    PositionContactManager manager =
                            new PositionContactManager(Modifier.this);
                     manager.modif(c,id);

                //long a=db.insert(PositionContactHelper.table_position,null,v);
                }
            }
        );


}}
