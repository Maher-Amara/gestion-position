package com.maherdev.gestionPosition;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PositionAdapter extends BaseAdapter {
    ArrayList<PositionContact> data;
    Context con;
    PositionContactManager manager;
    public PositionAdapter(Context con, ArrayList<PositionContact> data)
    {
        this.con=con;
        this.data=data;
        manager=new PositionContactManager(con);
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(con);
        View v = inflater.inflate(R.layout.view_position,null);
        TextView tvid=v.findViewById(R.id.tv_id_view);
        TextView tvnum=v.findViewById(R.id.tv_num_view);
        TextView tvps=v.findViewById(R.id.tv_pseudo_view);
        TextView tvlon=v.findViewById(R.id.tvlon_view);
        TextView tvlat=v.findViewById(R.id.tvlat_view);


        ImageButton sup = v.findViewById(R.id.imgbtn_sup);
        ImageButton call = v.findViewById(R.id.imageButton_call);
        ImageButton sms = v.findViewById(R.id.imageButton2_sms);
        ImageButton map = v.findViewById(R.id.imageButton3_map);
        final ImageButton fav = v.findViewById(R.id.imageButton_fav);
        ImageButton mod = v.findViewById(R.id.imageButton5_edit);
        final PositionContact pc= data.get(position);
        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fav.setImageResource(android.R.drawable.btn_star_big_on);
                //sauvegarde du contact ds un fichier
                File f= new File(Environment.getExternalStorageDirectory().getPath()+"favoris.csv");
                FileWriter writer = null;
                try {
                    writer = new FileWriter(f, true);
                    writer.write(pc.numero+","+pc.pseudo+","+pc.longitude+","+pc.latitude+"\n");
                    writer.close();

                    fav.setEnabled(false);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        mod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i;
                i = new Intent(con,Modifier.class);
                i.putExtra("id",pc.id);
                con.startActivity(i);
            }
        });
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                con.startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("geo:"+pc.longitude+","+pc.latitude)));

            }
        });
        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Uri u= Uri.parse("sms"+pc.numero+"sms_body=Ou est tu ?");
                // Intent i = new Intent(Intent.ACTION_SENDTO,u);
                Intent i= new Intent();
                i.setAction(Intent.ACTION_SENDTO);
                i.setData(Uri.parse("sms:"+pc.numero));
                i.putExtra("sms_body","Ou est tu ?");
                con.startActivity(i);
            }
        });

        sup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.supprimerSelonId(pc.id);
                data.remove(position);
                PositionAdapter.this.notifyDataSetChanged(); //actualiser l'affichage==>lv.invalideviews


            }
        });
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent();
                i.setAction(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:"+pc.numero));
                con.startActivities(new Intent[]{i});

            }
        });



        tvid.setText("idposition:" +pc.id);
        tvnum.setText(pc.numero);
        tvps.setText(pc.pseudo);
        tvlon.setText(pc.longitude);
        tvlat.setText(pc.latitude);

        return v;
    }
}
