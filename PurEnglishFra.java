package com.example.capston;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

//import android.app.Fragment;

public class PurEnglishFra extends Fragment {

    private TextView t1,t2;

    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_pur_english,
                container,false);
        return view;
    }

    public void onViewCreated(View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        t1=(TextView) view.findViewById(R.id.Title);
        t2=(TextView) view.findViewById(R.id.text);
    }
}

