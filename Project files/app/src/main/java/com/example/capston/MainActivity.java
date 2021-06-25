package com.example.capston;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.PopupWindowCompat;

import Care.CareActivity;
import Cleaning.EquipmentActivity;
import Disposal.DisposalActivity;
import Mixing.MixingActivity;
import My_plan.MyPlanActivity;
import Purchase.PurchaseActivity;
import Spraying.SprayingActivity;
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
        //Modify the status bar text to black
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        toolbar.setTitle("");
        //Sets whether the main title or subtitle should be realistic, hidden toolbar
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mToolbarTitle = findViewById(R.id.toolbar_title);

    }

    private void setListeners(){
        OnClick onClick=new OnClick();
        Mb1.setOnClickListener(onClick);
        Mb2.setOnClickListener(onClick);
        Mb3.setOnClickListener(onClick);
        Mb4.setOnClickListener(onClick);
        Mb5.setOnClickListener(onClick);
        Mb6.setOnClickListener(onClick);
        Mb7.setOnClickListener(onClick);
        Mb8.setOnClickListener(onClick);

    }

    private class OnClick implements View.OnClickListener{
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()) {
                case R.id.button:
                    intent = new Intent(MainActivity.this, PurchaseActivity.class);
                    if(Constants.isChinese){
                        Toast.makeText(MainActivity.this, "第一步被选中",
                                Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(MainActivity.this, "Step one is choosen",
                                Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.button2:
                    intent = new Intent(MainActivity.this, StorageActivity.class);
                    if(Constants.isChinese){
                        Toast.makeText(MainActivity.this, "第二步被选中",
                                Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(MainActivity.this, "Step two is choosen",
                                Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.button3:
                    intent = new Intent(MainActivity.this, DisposalActivity.class);
                    if(Constants.isChinese){
                        Toast.makeText(MainActivity.this, "第三步被选中",
                                Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(MainActivity.this, "Step three is choosen",
                                Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.button4:
                    intent = new Intent(MainActivity.this, SprayingActivity.class);
                    if(Constants.isChinese){
                        Toast.makeText(MainActivity.this, "第四步被选中",
                                Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(MainActivity.this, "Step four is choosen",
                                Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.button5:
                    intent = new Intent(MainActivity.this, MixingActivity.class);
                    if(Constants.isChinese){
                        Toast.makeText(MainActivity.this, "第五步被选中",
                                Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(MainActivity.this, "Step five is choosen",
                                Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.button6:
                    intent = new Intent(MainActivity.this, EquipmentActivity.class);
                    if(Constants.isChinese){
                        Toast.makeText(MainActivity.this, "第六步被选中",
                                Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(MainActivity.this, "Step six is choosen",
                                Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.button7:
                    intent = new Intent(MainActivity.this, CareActivity.class);
                    if(Constants.isChinese){
                        Toast.makeText(MainActivity.this, "第七步被选中",
                                Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(MainActivity.this, "Step seven is choosen",
                                Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.button8:
                    intent = new Intent(MainActivity.this, MyPlanActivity.class);
                    if(Constants.isChinese){
                        Toast.makeText(MainActivity.this, "第八步被选中",
                                Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(MainActivity.this, "Step eight is choosen",
                                Toast.LENGTH_SHORT).show();
                    }
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
        //Import the Menu layout
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    //Menu is ready to change the text.
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
        //Create the click event of the Menu
        switch (item.getItemId()) {
            case R.id.language:
                View view = LayoutInflater.from(this).inflate(R.layout.pop_language,null,false);
                final PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                //Settings can disappear by clicking on the outside
                popupWindow.setOutsideTouchable(true);
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        bgAlpha(1.0f);//After disappearing, restore brightness
                    }
                });
                TextView tvChinese = view.findViewById(R.id.tv_chinese);
                TextView tvEnglish = view.findViewById(R.id.tv_english);
                //Click the Chinese translation button
                tvChinese.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Constants.isChinese=true;
                        item.setTitle("中文");
                        updateLanguage();
                        popupWindow.dismiss();
                    }
                });
                //Click the English translation button
                tvEnglish.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Constants.isChinese=false;
                        item.setTitle("English");
                        updateLanguage();
                        popupWindow.dismiss();
                    }
                });
                bgAlpha(0.6f);//Show pop, background grayed out
                //Use androidx method to pop up popup compatible with the old version
                PopupWindowCompat.showAsDropDown(popupWindow,toolbar,-20,0, Gravity.RIGHT);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Set the background transparency of the window
     * @param f 0.0-1.0
     */
    private  void bgAlpha(float f){
        WindowManager.LayoutParams layoutParams =getWindow().getAttributes();
        layoutParams.alpha = f;
        getWindow().setAttributes(layoutParams);
    }


    //Unified modification and switching method between Chinese and English
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

            //Edit title
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
