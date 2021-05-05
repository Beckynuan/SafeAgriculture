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

public class MainFragment1 extends Fragment {
    private Button Mb1;
    private Button Mb2;

    private Button Mb8;

    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_main_fragment1,
                container,false);
        return view;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Mb1=(Button)view.findViewById(R.id.button);
        Mb2=(Button)view.findViewById(R.id.button2);

        Mb8=(Button)view.findViewById(R.id.button8);

        Mb1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), PurchaseActivity.class);
                Toast.makeText(getActivity(), "Step 1 is chosen",
                        Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

        Mb2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), PurchaseActivity.class);
                Toast.makeText(getActivity(), "Step 2 is chosen",
                        Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

        Mb8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), MyPlanActivity.class);
                Toast.makeText(getActivity(), "Step 8 is chosen",
                        Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }
}


