package My_plan.Weeds;

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

public class WeedsActivity extends AppCompatActivity {
    private Button Wb1;
    private Button Wb2;
    private Button Wb3;
    private Button Wb4;
    private Button Wb5;
    private Button Wb6;
    private Button Wb7;
    private Button Wb8;
    private Button Wb9;
    private Button Wb10;


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
        setContentView(R.layout.activity_weeds);

        Wb1 = (Button) findViewById(R.id.Wb1);
        Wb2 = (Button) findViewById(R.id.Wb2);
        Wb3 = (Button) findViewById(R.id.Wb3);
        Wb4 = (Button) findViewById(R.id.Wb4);
        Wb5 = (Button) findViewById(R.id.Wb5);
        Wb6 = (Button) findViewById(R.id.Wb6);
        Wb7 = (Button) findViewById(R.id.Wb7);
        Wb8 = (Button) findViewById(R.id.Wb8);
        Wb9 = (Button) findViewById(R.id.Wb9);
        Wb10 = (Button) findViewById(R.id.Wb10);
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
        Wb1.setOnClickListener(onClick);
        Wb2.setOnClickListener(onClick);
        Wb3.setOnClickListener(onClick);
        Wb4.setOnClickListener(onClick);
        Wb5.setOnClickListener(onClick);
        Wb6.setOnClickListener(onClick);
        Wb7.setOnClickListener(onClick);
        Wb8.setOnClickListener(onClick);
        Wb9.setOnClickListener(onClick);
        Wb10.setOnClickListener(onClick);
    }

    private class OnClick implements View.OnClickListener{
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()) {
                case R.id.Wb1:
                    intent = new Intent(WeedsActivity.this, Item1_Activity.class);
                    Toast.makeText(WeedsActivity.this, "Atrazine is chosen",
                            Toast.LENGTH_SHORT).show();
                    break;
                case R.id.Wb2:
                    intent = new Intent(WeedsActivity.this, Item1_Activity.class);
                    Toast.makeText(WeedsActivity.this, "S-metolachlor is chosen",
                            Toast.LENGTH_SHORT).show();
                    break;
                case R.id.Wb3:
                    intent = new Intent(WeedsActivity.this, Item1_Activity.class);
                    Toast.makeText(WeedsActivity.this, "Mesotrione is chosen",
                            Toast.LENGTH_SHORT).show();
                    break;
                case R.id.Wb4:
                    intent = new Intent(WeedsActivity.this, Item1_Activity.class);
                    Toast.makeText(WeedsActivity.this, "Bicyclopyrone is chosen",
                            Toast.LENGTH_SHORT).show();
                    break;
                case R.id.Wb5:
                    intent = new Intent(WeedsActivity.this, Item1_Activity.class);
                    Toast.makeText(WeedsActivity.this, "Triasulfuron is chosen",
                            Toast.LENGTH_SHORT).show();
                    break;
                case R.id.Wb6:
                    intent = new Intent(WeedsActivity.this, Item1_Activity.class);
                    Toast.makeText(WeedsActivity.this, "Pinoxaden is chosen",
                            Toast.LENGTH_SHORT).show();
                    break;
                case R.id.Wb7:
                    intent = new Intent(WeedsActivity.this, Item1_Activity.class);
                    Toast.makeText(WeedsActivity.this, "Sulfentrazone is chosen",
                            Toast.LENGTH_SHORT).show();
                    break;
                case R.id.Wb8:
                    intent = new Intent(WeedsActivity.this, Item1_Activity.class);
                    Toast.makeText(WeedsActivity.this, "Glyphosate is chosen",
                            Toast.LENGTH_SHORT).show();
                    break;
                case R.id.Wb9:
                    intent = new Intent(WeedsActivity.this, Item1_Activity.class);
                    Toast.makeText(WeedsActivity.this, "Clodinafop-propargyl is chosen",
                            Toast.LENGTH_SHORT).show();
                    break;
                case R.id.Wb10:
                    intent = new Intent(WeedsActivity.this, Item1_Activity.class);
                    Toast.makeText(WeedsActivity.this, "Benoxacor is chosen",
                            Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
            startActivity(intent);
        }
    }
}