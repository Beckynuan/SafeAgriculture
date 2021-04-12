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

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class PurchaseActivity extends AppCompatActivity {
    private PurEnglishFra aFragement;
    private PurChineseFra bFragement;
    private Fragment mFragment;//当前显示的Fragment
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch mSwitch;


    private SharedPreferences spre;

    YouTubePlayerView palyer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);
        palyer = (YouTubePlayerView) findViewById(R.id.youtube);
        getLifecycle().addObserver(palyer);
        spre = getSharedPreferences("translate", Activity.MODE_PRIVATE);
        mSwitch = (Switch) findViewById(R.id.btn_layout);
        final TextView switchBtn_txtView = (TextView) findViewById(R.id.switchBtn_txtView);
        aFragement = new PurEnglishFra();
        bFragement = new PurChineseFra();
        boolean isChinses = spre.getBoolean("isChinese", false);
        if (isChinses) {
            mSwitch.setChecked(true);
            getSupportFragmentManager().beginTransaction().
                    add(R.id.fl_container, bFragement).commitAllowingStateLoss();
            mFragment = bFragement;
        } else {
            mSwitch.setChecked(false);
            getSupportFragmentManager().beginTransaction().
                    add(R.id.fl_container, aFragement).commitAllowingStateLoss();
            mFragment = aFragement;
        }

        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = spre.edit();
                if (isChecked) {
                    switchBtn_txtView.setText("Chinese");
                    editor.putBoolean("isChinese", true);
                } else {
                    switchBtn_txtView.setText("English");
                    editor.putBoolean("isChinese", false);
                }
                editor.commit();
            }
        });

        mSwitch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (mFragment == bFragement) {
                    switchFragment(aFragement);
                } else {
                    switchFragment(bFragement);
                }
            }
        });

    }


        private void switchFragment(Fragment fragment){
            //判断当前显示的Fragment是不是切换的Fragment
            if (mFragment != fragment) {
                //判断切换的Fragment是否已经添加过
                if (!fragment.isAdded()) {
                    //如果没有，则先把当前的Fragment隐藏，把切换的Fragment添加上
                    getSupportFragmentManager().beginTransaction().hide(mFragment)
//                getSupportFragmentManager().beginTransaction().remove(mFragment)//销毁
                            .add(R.id.fl_container, fragment).commit();
                } else {
                    //如果已经添加过，则先把当前的Fragment隐藏，把切换的Fragment显示出来
                    getSupportFragmentManager().beginTransaction().hide(mFragment).show(fragment).commit();
                }
                mFragment = fragment;
            }
        }
    }
