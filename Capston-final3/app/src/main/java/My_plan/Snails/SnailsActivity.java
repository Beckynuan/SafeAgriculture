package My_plan.Snails;

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

import My_plan.Insect.Item1_Activity;

public class SnailsActivity extends AppCompatActivity {

    private Button sb1;
    private Button sb2;
    private Button sb3;
    private Button sb4;
    private Button sb5;

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
        setContentView(R.layout.activity_snails);

        sb1 = (Button) findViewById(R.id.sb1);
        sb2 = (Button) findViewById(R.id.sb2);
        sb3 = (Button) findViewById(R.id.sb3);
        sb4 = (Button) findViewById(R.id.sb4);
        sb5 = (Button) findViewById(R.id.sb5);
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