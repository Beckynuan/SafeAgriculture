package My_plan.Snails;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.capston.BitmapDescriptorFactory;
import com.example.capston.MainActivity;
import com.example.capston.R;

import My_plan.Insect.Item1_Activity;
import My_plan.Rats.RatsActivity;

public class SnailsActivity extends AppCompatActivity {

    private Button sb1;
    private Button sb2;
    private Button sb3;
    private Button sb4;
    private Button sb5;

    private Toolbar toolbar;
    private TextView mToolbarTitle;
    private TextView mTvBack;
    private View mBackView;
    private BitmapDescriptorFactory bitmapDescriptorFactory=new BitmapDescriptorFactory();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snails);

        sb1 = (Button) findViewById(R.id.sb1);
        sb2 = (Button) findViewById(R.id.sb2);
        sb3 = (Button) findViewById(R.id.sb3);
        sb4 = (Button) findViewById(R.id.sb4);
        sb5 = (Button) findViewById(R.id.sb5);
        setListeners();

        mToolbarTitle = findViewById(R.id.toolbar_title);
        toolbar = findViewById(R.id.toolbar);
        mBackView = LayoutInflater.from(this).inflate(R.layout.view_back, null, false);
        mTvBack = mBackView.findViewById(R.id.tv_back);

        setSupportActionBar(toolbar);
        toolbar.setTitle("");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setNavigationIcon(bitmapDescriptorFactory.fromView(mBackView));
        //Back button click event
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        //Import menu layout
        getMenuInflater().inflate(R.menu.main_home, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        //Create a click event for a menu item
        switch (item.getItemId()) {
            case R.id.homeButton:
                Intent intent=new Intent(SnailsActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setListeners(){
        OnClick onClick=new OnClick();
        sb1.setOnClickListener(onClick);
        sb2.setOnClickListener(onClick);
        sb3.setOnClickListener(onClick);
        sb4.setOnClickListener(onClick);
        sb5.setOnClickListener(onClick);
    }

    private class OnClick implements View.OnClickListener{
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()) {
                case R.id.sb1:
                    intent = new Intent(SnailsActivity.this, Item1_Activity.class);
                    Toast.makeText(SnailsActivity.this, "Metaldehyde is chosen",
                            Toast.LENGTH_SHORT).show();
                    break;
                case R.id.sb2:
                    intent = new Intent(SnailsActivity.this, Item1_Activity.class);
                    Toast.makeText(SnailsActivity.this, "Methiocarb is chosen",
                            Toast.LENGTH_SHORT).show();
                    break;
                case R.id.sb3:
                    intent = new Intent(SnailsActivity.this, Item1_Activity.class);
                    Toast.makeText(SnailsActivity.this, "Acetylcholinesterase inhibitors is chosen",
                            Toast.LENGTH_SHORT).show();
                    break;
                case R.id.sb4:
                    intent = new Intent(SnailsActivity.this, Item1_Activity.class);
                    Toast.makeText(SnailsActivity.this, "Ferric phosphate is chosen",
                            Toast.LENGTH_SHORT).show();
                    break;
                case R.id.sb5:
                    intent = new Intent(SnailsActivity.this, Item1_Activity.class);
                    Toast.makeText(SnailsActivity.this, "Phosphoric Acid is chosen",
                            Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
            startActivity(intent);
        }
    }
}