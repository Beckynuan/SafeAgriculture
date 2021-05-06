package My_plan.Disease;

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

import My_plan.Insect.InsectsActivity;
import My_plan.Insect.Item1_Activity;


public class DiseasesActivity extends AppCompatActivity {
    private Button Db1;
    private Button Db2;
    private Button Db3;
    private Button Db4;
    private Button Db5;
    private Button Db6;
    private Button Db7;
    private Button Db8;
    private Button Db9;
    private Button Db10;


    private Toolbar toolbar;
    private TextView mToolbarTitle;
    private TextView mTvBack;
    private View mBackView;
    //View 转化Drawable辅助类
    private BitmapDescriptorFactory bitmapDescriptorFactory=new BitmapDescriptorFactory();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diseases);

        Db1=(Button)findViewById(R.id.Db1);
        Db2=(Button)findViewById(R.id.Db2);
        Db3=(Button)findViewById(R.id.Db3);
        Db4=(Button)findViewById(R.id.Db4);
        Db5=(Button)findViewById(R.id.Db5);
        Db6=(Button)findViewById(R.id.Db6);
        Db7=(Button)findViewById(R.id.Db7);
        Db8=(Button)findViewById(R.id.Db8);
        Db9=(Button)findViewById(R.id.Db9);
        Db10=(Button)findViewById(R.id.Db10);
        setListeners();

        toolbar=findViewById(R.id.toolbar);
        mBackView = LayoutInflater.from(this).inflate(R.layout.view_back, null, false);
        mTvBack = mBackView.findViewById(R.id.tv_back);
        mToolbarTitle = findViewById(R.id.toolbar_title);

        setSupportActionBar(toolbar);
        toolbar.setTitle("");

        //修改状态栏为文字为黑色
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setNavigationIcon(bitmapDescriptorFactory.fromView(mBackView));
        //返回按钮点击事件
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        //导入菜单布局
        getMenuInflater().inflate(R.menu.main_home, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        //创建菜单项的点击事件
        switch (item.getItemId()) {
            case R.id.homeButton:
                Intent intent=new Intent(DiseasesActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setListeners(){
        OnClick onClick=new OnClick();
        Db1.setOnClickListener(onClick);
        Db2.setOnClickListener(onClick);
        Db3.setOnClickListener(onClick);
        Db4.setOnClickListener(onClick);
        Db5.setOnClickListener(onClick);
        Db6.setOnClickListener(onClick);
        Db7.setOnClickListener(onClick);
        Db8.setOnClickListener(onClick);
        Db9.setOnClickListener(onClick);
        Db10.setOnClickListener(onClick);

    }

    private class OnClick implements View.OnClickListener{
        public void onClick(View v){
            Intent intent=null;
            switch(v.getId()){
                case R.id.Db1:
                    intent=new Intent(DiseasesActivity.this, Item1_Activity.class);
                    Toast.makeText(DiseasesActivity.this, "Dithane is chosen",
                            Toast.LENGTH_SHORT).show();
                    break;
                case R.id.Db2:
                    intent=new Intent(DiseasesActivity.this, Item1_Activity.class);
                    Toast.makeText(DiseasesActivity.this, "Azoxystrobin is chosen",
                            Toast.LENGTH_SHORT).show();
                    break;
                case R.id.Db3:
                    intent=new Intent(DiseasesActivity.this, Item1_Activity.class);
                    Toast.makeText(DiseasesActivity.this, "Difenoconazole is chosen",
                            Toast.LENGTH_SHORT).show();
                    break;
                case R.id.Db4:
                    intent=new Intent(DiseasesActivity.this, Item1_Activity.class);
                    Toast.makeText(DiseasesActivity.this, "Fludioxonil is chosen",
                            Toast.LENGTH_SHORT).show();
                    break;
                case R.id.Db5:
                    intent=new Intent(DiseasesActivity.this, Item1_Activity.class);
                    Toast.makeText(DiseasesActivity.this, "Acibenzolar-S-methyl is chosen",
                            Toast.LENGTH_SHORT).show();
                    break;
                case R.id.Db6:
                    intent=new Intent(DiseasesActivity.this, Item1_Activity.class);
                    Toast.makeText(DiseasesActivity.this, "Thiabendazole is chosen",
                            Toast.LENGTH_SHORT).show();
                    break;
                case R.id.Db7:
                    intent=new Intent(DiseasesActivity.this, Item1_Activity.class);
                    Toast.makeText(DiseasesActivity.this, "Cyproconozole is chosen",
                            Toast.LENGTH_SHORT).show();
                    break;
                case R.id.Db8:
                    intent=new Intent(DiseasesActivity.this, Item1_Activity.class);
                    Toast.makeText(DiseasesActivity.this, "Propiconazole is chosen",
                            Toast.LENGTH_SHORT).show();
                    break;
                case R.id.Db9:
                    intent=new Intent(DiseasesActivity.this, Item1_Activity.class);
                    Toast.makeText(DiseasesActivity.this, "Cyprodinil is chosen",
                            Toast.LENGTH_SHORT).show();
                    break;
                case R.id.Db10:
                    intent=new Intent(DiseasesActivity.this, Item1_Activity.class);
                    Toast.makeText(DiseasesActivity.this, "Pydiflumetofen is chosen",
                            Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
            startActivity(intent);
        }
    }
}