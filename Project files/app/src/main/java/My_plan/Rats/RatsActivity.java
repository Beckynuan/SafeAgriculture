package My_plan.Rats;

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
import My_plan.Weeds.WeedsActivity;

public class RatsActivity extends AppCompatActivity {
    private Button rb1;
    private Button rb2;
    private Button rb3;
    private Button rb4;
    private Button rb5;
    private Button rb6;
    private Button rb7;
    private Button rb8;
    private Button rb9;
    private Button rb10;

    private Toolbar toolbar;
    private TextView mToolbarTitle;
    private TextView mTvBack;
    private View mBackView;
    private BitmapDescriptorFactory bitmapDescriptorFactory=new BitmapDescriptorFactory();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rats);

        rb1 = (Button) findViewById(R.id.rb1);
        rb2 = (Button) findViewById(R.id.rb2);
        rb3 = (Button) findViewById(R.id.rb3);
        rb4 = (Button) findViewById(R.id.rb4);
        rb5 = (Button) findViewById(R.id.rb5);
        rb6 = (Button) findViewById(R.id.rb6);
        rb7 = (Button) findViewById(R.id.rb7);
        rb8 = (Button) findViewById(R.id.rb8);
        rb9 = (Button) findViewById(R.id.rb9);
        rb10 = (Button) findViewById(R.id.rb10);
        setListeners();

        toolbar = findViewById(R.id.toolbar);
        mBackView = LayoutInflater.from(this).inflate(R.layout.view_back, null, false);
        mTvBack = mBackView.findViewById(R.id.tv_back);
        mToolbarTitle = findViewById(R.id.toolbar_title);

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
                Intent intent=new Intent(RatsActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setListeners(){
        OnClick onClick=new OnClick();
        rb1.setOnClickListener(onClick);
        rb2.setOnClickListener(onClick);
        rb3.setOnClickListener(onClick);
        rb4.setOnClickListener(onClick);
        rb5.setOnClickListener(onClick);
        rb6.setOnClickListener(onClick);
        rb7.setOnClickListener(onClick);
        rb8.setOnClickListener(onClick);
        rb9.setOnClickListener(onClick);
        rb10.setOnClickListener(onClick);
    }

    private class OnClick implements View.OnClickListener{
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()) {
                case R.id.rb1:
                    intent = new Intent(RatsActivity.this, Item1_Activity.class);
                    Toast.makeText(RatsActivity.this, "Brodifacoum is chosen",
                            Toast.LENGTH_SHORT).show();
                    break;
                case R.id.rb2:
                    intent = new Intent(RatsActivity.this, Item1_Activity.class);
                    Toast.makeText(RatsActivity.this, "Cholecalciferol is chosen",
                            Toast.LENGTH_SHORT).show();
                    break;
                case R.id.rb3:
                    intent = new Intent(RatsActivity.this, Item1_Activity.class);
                    Toast.makeText(RatsActivity.this, "Bromethalin is chosen",
                            Toast.LENGTH_SHORT).show();
                    break;
                case R.id.rb4:
                    intent = new Intent(RatsActivity.this, Item1_Activity.class);
                    Toast.makeText(RatsActivity.this, "Chlorphacinone is chosen",
                            Toast.LENGTH_SHORT).show();
                    break;
                case R.id.rb5:
                    intent = new Intent(RatsActivity.this, Item1_Activity.class);
                    Toast.makeText(RatsActivity.this, "Diphacinone is chosen",
                            Toast.LENGTH_SHORT).show();
                    break;
                case R.id.rb6:
                    intent = new Intent(RatsActivity.this, Item1_Activity.class);
                    Toast.makeText(RatsActivity.this, "Bromadiolone is chosen",
                            Toast.LENGTH_SHORT).show();
                    break;
                case R.id.rb7:
                    intent = new Intent(RatsActivity.this, Item1_Activity.class);
                    Toast.makeText(RatsActivity.this, "Difethialone is chosen",
                            Toast.LENGTH_SHORT).show();
                    break;
                case R.id.rb8:
                    intent = new Intent(RatsActivity.this, Item1_Activity.class);
                    Toast.makeText(RatsActivity.this, "Zinc phosphide is chosen",
                            Toast.LENGTH_SHORT).show();
                    break;
                case R.id.rb9:
                    intent = new Intent(RatsActivity.this, Item1_Activity.class);
                    Toast.makeText(RatsActivity.this, "Strychnine is chosen",
                            Toast.LENGTH_SHORT).show();
                    break;
                case R.id.rb10:
                    intent = new Intent(RatsActivity.this, Item1_Activity.class);
                    Toast.makeText(RatsActivity.this, "Warfarin is chosen",
                            Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
            startActivity(intent);
        }
    }
}