package com.maherdev.gestionPosition.ui.notifications;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.maherdev.gestionPosition.PositionAdapter;
import com.maherdev.gestionPosition.PositionContact;
import com.maherdev.gestionPosition.PositionContactManager;
import com.maherdev.gestionPosition.R;

import java.util.ArrayList;

public class NotificationsFragment extends Fragment {
    ListView lv;
    EditText edrech;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        lv = root.findViewById(R.id.lv);
        edrech =root.findViewById(R.id.ed_rech_notif);

        PositionContactManager manager =
                new PositionContactManager(this.getActivity());
        ArrayList<PositionContact> data = manager.getAllPosition();

        PositionAdapter adapter = new PositionAdapter(
                getActivity(),
                data);
        lv.setAdapter(adapter);

       edrech.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return root;
    }
}