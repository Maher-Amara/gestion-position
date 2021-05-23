package com.maherdev.gestionposition.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.maherdev.gestionposition.PositionContactManager;
import com.maherdev.gestionposition.R;

public class DashboardFragment extends Fragment {

    EditText edlat,edlong,edpseudo,ednum;
    Button btnajout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        LinearLayout root = (LinearLayout)inflater.inflate(R.layout.fragment_dashboard, container, false);
        btnajout = root.findViewById(R.id.btnajout);
        edlat = root.findViewById(R.id.edlatitude);
        edlong = root.findViewById(R.id.edlongitude);
        edpseudo = root.findViewById(R.id.edpseudo);
        ednum = root.findViewById(R.id.ednumero);
        btnajout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num = ednum.getText().toString();
                String ps = edpseudo.getText().toString();
                String lat = edlat.getText().toString();
                String lon = edlong.getText().toString();
                //enregistrement ds la base:
                if(num.isEmpty()||ps.isEmpty()||lat.isEmpty()||lon.isEmpty())
                    Toast.makeText(getActivity(),"champ vide",Toast.LENGTH_SHORT).show();
                else {
                    PositionContactManager manager =
                            new PositionContactManager(DashboardFragment.this.getActivity());
                    long a = manager.inserer(num, ps, lon, lat);

                    if (a > 0) {
                        Toast.makeText(getActivity(), "Sucess", Toast.LENGTH_SHORT).show();
                        ednum.setText("");
                        edpseudo.setText("");
                        edlong.setText("");
                        edlat.setText("");
                    } else {
                        Toast.makeText(getActivity(), "echec", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return root;
    }
}