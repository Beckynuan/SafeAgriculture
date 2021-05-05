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

import My_plan.MyPlanActivity;
import Purchase.PurchaseActivity;

public class MainFragment2 extends Fragment {
    private Button MCb1;
    private Button MCb8;

    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_main_fragment2,
                container,false);
        return view;
    }

    public void onViewCreated(View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MCb1=(Button)view.findViewById(R.id.Cbutton);

        MCb8=(Button)view.findViewById(R.id.Cbutton8);

        MCb1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), PurchaseActivity.class);
                Toast.makeText(getActivity(), "第一步被选中", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

        MCb8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyPlanActivity.class);
                Toast.makeText(getActivity(), "第八步被选中", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }
}

