package com.maherdev.gestionposition.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import com.maherdev.gestionposition.PositionAdapter;
import com.maherdev.gestionposition.PositionContact;
import com.maherdev.gestionposition.PositionContactManager;
import com.maherdev.gestionposition.R;

import java.util.ArrayList;

public class NotificationsFragment extends Fragment {
    ListView lv;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        lv = root.findViewById(R.id.lv);
        PositionContactManager manager =
                new PositionContactManager(com.maherdev.gestionposition.ui.notifications.NotificationsFragment.this.getActivity());
        ArrayList<PositionContact> data = manager.getAllPosition();
        PositionAdapter adapter = new PositionAdapter(
                getActivity(),
                data);
        lv.setAdapter(adapter);
        return root;
    }
}