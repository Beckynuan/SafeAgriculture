package com.example.capston;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.service.autofill.OnClickAction;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.PopupWindowCompat;
import androidx.fragment.app.Fragment;

import My_plan.Insect.InsectsActivity;
import My_plan.Insect.Item1_Activity;
import My_plan.MyPlanActivity;
import Purchase.PurchaseActivity;
import Storage.StorageActivity;

public class MainActivity extends AppCompatActivity {
    private Button Mb1;
    private Button Mb2;
    private Button Mb3;
    private Button Mb4;
    private Button Mb5;
    private Button Mb6;
    private Button Mb7;
    private Button Mb8;

    private Toolbar toolbar;
    private TextView mToolbarTitle;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Mb1=(Button)findViewById(R.id.button);
        Mb2=(Button)findViewById(R.id.button2);
        Mb3=(Button)findViewById(R.id.button3);
        Mb4=(Button)findViewById(R.id.button4);
        Mb5=(Button)findViewById(R.id.button5);
        Mb6=(Button)findViewById(R.id.button6);
        Mb7=(Button)findViewById(R.id.button7);
        Mb8=(Button)findViewById(R.id.button8);
        setListeners();

        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //修改状态栏为文字为黑色
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        toolbar.setTitle("");
        // 设置主标题或者子标题是否应该被显示，隐藏掉toolbar自带的主标题和子标题
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mToolbarTitle = findViewById(R.id.toolbar_title);

    }

    private void setListeners(){
        OnClick onClick=new OnClick();
        Mb1.setOnClickListener(onClick);
        Mb2.setOnClickListener(onClick);

        Mb8.setOnClickListener(onClick);

    }

    private class OnClick implements View.OnClickListener{
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()) {
                case R.id.button:
                    intent = new Intent(MainActivity.this, PurchaseActivity.class);
//                    Toast.makeText(MainActivity.this, "Thiamethoxam is chosen",
//                            Toast.LENGTH_SHORT).show();
                    break;
                case R.id.button2:
                    intent = new Intent(MainActivity.this, StorageActivity.class);
                    break;
                case R.id.button8:
                    intent = new Intent(MainActivity.this, MyPlanActivity.class);
                    break;
                default:
                    break;
            }
            startActivity(intent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateLanguage();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        //导入菜单布局
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    //menu已经准备好，可以修改文字
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if(Constants.isChinese){
            menu.getItem(0).setTitle("中文");
        }else {
            menu.getItem(0).setTitle("English");
        }

        return super.onPrepareOptionsMenu(menu);

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        //创建菜单项的点击事件
        switch (item.getItemId()) {
            case R.id.language:
                View view = LayoutInflater.from(this).inflate(R.layout.pop_language,null,false);
               final PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                //设置点击外侧可以消失
                popupWindow.setOutsideTouchable(true);
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        bgAlpha(1.0f);//消失后,恢复亮度
                    }
                });
                TextView tvChinese = view.findViewById(R.id.tv_chinese);
                TextView tvEnglish = view.findViewById(R.id.tv_english);
                //点击中文
                tvChinese.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Constants.isChinese=true;
                        item.setTitle("中文");
                        updateLanguage();
                        popupWindow.dismiss();
                    }
                });
                //点击英文
                tvEnglish.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Constants.isChinese=false;
                        item.setTitle("English");
                        updateLanguage();
                        popupWindow.dismiss();
                    }
                });
                bgAlpha(0.6f);//显示pop,背景变灰
                //使用androidx 方式弹出popup兼容老版本
                PopupWindowCompat.showAsDropDown(popupWindow,toolbar,-20,0, Gravity.RIGHT);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 设置窗口的背景透明度
     * @param f 0.0-1.0
     */
    private  void bgAlpha(float f){
        WindowManager.LayoutParams layoutParams =getWindow().getAttributes();
        layoutParams.alpha = f;
       getWindow().setAttributes(layoutParams);
    }


    //统一修改切换中英文方法
    private void updateLanguage(){
        if (Constants.isChinese) {
            Mb1.setText("购买");
            Mb2.setText("储存");
            Mb3.setText("处置");
            Mb4.setText("喷洒");
            Mb5.setText("混合");
            Mb6.setText("清洗设备");
            Mb7.setText("安全防护");
            Mb8.setText("我的计划");

            //修改标题
            mToolbarTitle.setText("首页");
            this.invalidateOptionsMenu();

        } else {
            Mb1.setText("PURCHASE PRODUCT");
            Mb2.setText("SAFE STORAGE");
            Mb3.setText("SAFE DISPOSAL");
            Mb4.setText("SPRAYING");
            Mb5.setText("MIXING");
            Mb6.setText("CLEANING EQUIPMENT");
            Mb7.setText("SAFE CARE");
            Mb8.setText("MY PLAN");

            mToolbarTitle.setText("Home Page");
            this.invalidateOptionsMenu();

        }
    }



    private static final String TAG = "jcy-MainActivity";
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.d(TAG, "onConfigurationChanged: 横屏");
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Log.d(TAG, "onConfigurationChanged: 竖屏");
            getActionBar().show();
        }
    }
}