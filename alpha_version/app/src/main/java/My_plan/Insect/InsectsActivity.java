package My_plan.Insect;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.capston.BitmapDescriptorFactory;
import com.example.capston.R;

public class InsectsActivity extends AppCompatActivity {
    private Button b1;
    private Button b2;
    private Button b3;
    private Button b4;
    private Button b5;
    private Button b6;
    private Button b7;
    private Button b8;
    private Button b9;
    private Button b10;


    private Toolbar toolbar;
    private TextView mToolbarTitle;
    private TextView mToolbarTitle2;
    private TextView mTvBack;
    private View mBackView;
    //View 转化Drawable辅助类
    private BitmapDescriptorFactory bitmapDescriptorFactory=new BitmapDescriptorFactory();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insects);

        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        b3 = (Button) findViewById(R.id.b3);
        b4 = (Button) findViewById(R.id.b4);
        b5 = (Button) findViewById(R.id.b5);
        b6 = (Button) findViewById(R.id.b6);
        b7 = (Button) findViewById(R.id.b7);
        b8 = (Button) findViewById(R.id.b8);
        b9 = (Button) findViewById(R.id.b9);
        b10 = (Button) findViewById(R.id.b10);
        setListeners();

        toolbar = findViewById(R.id.toolbar);
        mBackView = LayoutInflater.from(this).inflate(R.layout.view_back, null, false);
        mTvBack = mBackView.findViewById(R.id.tv_back);

        //setSupportActionBar(toolbar);
        //修改状态栏为文字为黑色
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        mToolbarTitle = findViewById(R.id.toolbar_title);
        mToolbarTitle2 = findViewById(R.id.toolbar_title);
        //mToolbarTitle.setText("My plan");

        // 设置主标题或者子标题是否应该被显示，隐藏掉toolbar自带的主标题和子标题
        //getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setNavigationIcon(bitmapDescriptorFactory.fromView(mBackView));
        //返回按钮点击事件
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setListeners(){
        OnClick onClick=new OnClick();
        b1.setOnClickListener(onClick);
        b2.setOnClickListener(onClick);
        b3.setOnClickListener(onClick);
        b4.setOnClickListener(onClick);
        b5.setOnClickListener(onClick);
        b6.setOnClickListener(onClick);
        b7.setOnClickListener(onClick);
        b8.setOnClickListener(onClick);
        b9.setOnClickListener(onClick);
        b10.setOnClickListener(onClick);
    }


    private class OnClick implements View.OnClickListener{
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()) {
                case R.id.b1:
                    intent = new Intent(InsectsActivity.this, Item1_Activity.class);
                    Toast.makeText(InsectsActivity.this, "Thiamethoxam is chosen",
                            Toast.LENGTH_SHORT).show();
                    break;
                case R.id.b2:
                    intent = new Intent(InsectsActivity.this, Item1_Activity.class);
                    Toast.makeText(InsectsActivity.this, "Abamectin is chosen",
                            Toast.LENGTH_SHORT).show();
                    break;
                case R.id.b3:
                    intent = new Intent(InsectsActivity.this, Item1_Activity.class);
                    Toast.makeText(InsectsActivity.this, "Chlorantraniliprole is chosen",
                            Toast.LENGTH_SHORT).show();
                    break;
                case R.id.b4:
                    intent = new Intent(InsectsActivity.this, Item1_Activity.class);
                    Toast.makeText(InsectsActivity.this, "Emamectin benzoate is chosen",
                            Toast.LENGTH_SHORT).show();
                    break;
                case R.id.b5:
                    intent = new Intent(InsectsActivity.this, Item1_Activity.class);
                    Toast.makeText(InsectsActivity.this, "Tefluthrin is chosen",
                            Toast.LENGTH_SHORT).show();
                    break;
                case R.id.b6:
                    intent = new Intent(InsectsActivity.this, Item1_Activity.class);
                    Toast.makeText(InsectsActivity.this, "Cyantraniliprole is chosen",
                            Toast.LENGTH_SHORT).show();
                    break;
                case R.id.b7:
                    intent = new Intent(InsectsActivity.this, Item1_Activity.class);
                    Toast.makeText(InsectsActivity.this, "Lambda-cyhalothrin is chosen",
                            Toast.LENGTH_SHORT).show();
                    break;
                case R.id.b8:
                    intent = new Intent(InsectsActivity.this, Item1_Activity.class);
                    Toast.makeText(InsectsActivity.this, "Cyfluthrin is chosen",
                            Toast.LENGTH_SHORT).show();
                    break;
                case R.id.b9:
                    intent = new Intent(InsectsActivity.this, Item1_Activity.class);
                    Toast.makeText(InsectsActivity.this, "Fipronil is chosen",
                            Toast.LENGTH_SHORT).show();
                    break;
                case R.id.b10:
                    intent = new Intent(InsectsActivity.this, Item1_Activity.class);
                    Toast.makeText(InsectsActivity.this, "Permethrin is chosen",
                            Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
            startActivity(intent);
        }
    }
}