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

public class PlanFragment1 extends Fragment {
    private Button b1;
    private Button b2;
    private Button b3;
    private Button b4;
    private Button b5;

    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_plan_fragment1,
                container,false);
        return view;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        b1 = (Button) view.findViewById(R.id.Pbutton);
        b2 = (Button) view.findViewById(R.id.Pbutton2);
        b3 = (Button) view.findViewById(R.id.Pbutton3);
        b4 = (Button) view.findViewById(R.id.Pbutton4);
        b5 = (Button) view.findViewById(R.id.Pbutton5);

        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), InsectsActivity.class);
                Toast.makeText(getActivity(), "Step 1 is chosen", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), DiseasesActivity.class);
                Toast.makeText(getActivity(), "Step 2 is chosen", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), WeedsActivity.class);
                Toast.makeText(getActivity(), "Step 3 is chosen",
                        Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), RatsActivity.class);
                Toast.makeText(getActivity(), "Step 4 is chosen",
                        Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), SnailsActivity.class);
                Toast.makeText(getActivity(), "Step 5 is chosen",
                        Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }
}