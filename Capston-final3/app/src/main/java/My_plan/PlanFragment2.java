package My_plan;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import My_plan.Disease.DiseasesActivity;
import My_plan.Insect.InsectsActivity;
import com.example.capston.R;
import My_plan.Rats.RatsActivity;
import My_plan.Snails.SnailsActivity;
import My_plan.Weeds.WeedsActivity;

public class PlanFragment2 extends Fragment {
    private Button Cb1;
    private Button Cb2;
    private Button Cb3;
    private Button Cb4;
    private Button Cb5;

    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_plan_fragment2,
                container,false);
        return view;
    }

    public void onViewCreated(View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Cb1=(Button)view.findViewById(R.id.PCbutton);
        Cb2=(Button)view.findViewById(R.id.PCbutton2);
        Cb3=(Button)view.findViewById(R.id.PCbutton3);
        Cb4=(Button)view.findViewById(R.id.PCbutton4);
        Cb5=(Button)view.findViewById(R.id.PCbutton5);

        Cb1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), InsectsActivity.class);
                Toast.makeText(getActivity(), "第一步被选中", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
        Cb2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), DiseasesActivity.class);
                Toast.makeText(getActivity(), "第二步被选中", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
        Cb3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), WeedsActivity.class);
                Toast.makeText(getActivity(), "第三步被选中", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
        Cb4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), RatsActivity.class);
                Toast.makeText(getActivity(), "第四步被选中", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
        Cb5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), SnailsActivity.class);
                Toast.makeText(getActivity(), "第五步被选中", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }
}