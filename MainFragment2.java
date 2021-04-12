package com.example.capston;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MainFragment2 extends Fragment {
    private Button Cb1;

    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_main_fragment2,
                container,false);
        return view;
    }

    public void onViewCreated(View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Cb1=(Button)view.findViewById(R.id.Cbutton);
        Cb1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), PurchaseActivity.class);
                Toast.makeText(getActivity(), "第一步被选中", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }
}

