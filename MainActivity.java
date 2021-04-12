package com.example.capston;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {

    private MainFragment1 mainFragment1;
    private MainFragment2 mainFragment2;
    private Fragment mFragment;//当前显示的Fragment
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch mSwitch;
    private SharedPreferences spre;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //取得一個 SharedPreferences 物件讓目前的 Activity 使用
        //產生的 SharedPreferences 檔案「無法讓其他 App 存取
        spre = getSharedPreferences("translate", Activity.MODE_PRIVATE);

        mSwitch=(Switch) findViewById(R.id.btn_layout);
        final TextView switchBtn_txtView = (TextView) findViewById(R.id.switchBtn_txtView);

        //实例化Afragement
        mainFragment1=new MainFragment1();
        mainFragment2=new MainFragment2();

        //透過 KEY_BOOL key 取出 boolean 型態的資料，若資料不存在則回傳 true
        boolean isChinses = spre.getBoolean("isChinese",false);
        if(isChinses){
            //更改此按钮的选中状态
            //boolean：选中按钮为true，取消选中为false
            mSwitch.setChecked(true);
            getSupportFragmentManager().beginTransaction().add(R.id.fl_container,mainFragment2).commitAllowingStateLoss();
            mFragment = mainFragment2;
        }else{
            mSwitch.setChecked(false);
            getSupportFragmentManager().beginTransaction().add(R.id.fl_container,mainFragment1).commitAllowingStateLoss();
            mFragment = mainFragment1;
        }

        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                //由 SharedPreferences 中取出 Editor 物件，透過 Editor 物件將資料存入
                //获取Editor
                SharedPreferences.Editor editor = spre.edit();
                if(isChecked){
                    switchBtn_txtView.setText("Chinese");
                    editor.putBoolean("isChinese", true);
                }
                else {
                    switchBtn_txtView.setText("English");
                    editor.putBoolean("isChinese", false);
                }
                //提交修改
                editor.commit();
            }
        });

        mSwitch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (mFragment == mainFragment2) {
                    switchFragment(mainFragment1);
                } else {
                    switchFragment(mainFragment2);
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        boolean isChinses = spre.getBoolean("isChinese",false);
        if(isChinses){
            mSwitch.setChecked(true);
            if (mFragment == mainFragment1) {
                switchFragment(mainFragment2);
            }
        }else{
            mSwitch.setChecked(false);
            if (mFragment == mainFragment2) {
                switchFragment(mainFragment1);
            }
        }
    }

    private void switchFragment(Fragment fragment) {
        if(mFragment != fragment) {
            if (!fragment.isAdded()) {
                getSupportFragmentManager().beginTransaction().hide(mFragment)
                        .add(R.id.fl_container,fragment).commit();
            } else {
                getSupportFragmentManager().beginTransaction().hide(mFragment).show(fragment).commit();
            }
            mFragment = fragment;
        }
    }
}