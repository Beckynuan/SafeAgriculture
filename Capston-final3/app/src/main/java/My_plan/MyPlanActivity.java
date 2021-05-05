package My_plan;

import android.annotation.SuppressLint;
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
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.PopupWindowCompat;
import androidx.fragment.app.Fragment;

import com.example.capston.BitmapDescriptorFactory;
import com.example.capston.Constants;
import com.example.capston.R;

public class MyPlanActivity extends AppCompatActivity {

    private PlanFragment1 planFragment1;
    private PlanFragment2 planFragment2;
    private Fragment mFragment;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Toolbar toolbar;
    private TextView mToolbarTitle;
    private TextView mTvBack;
    private View mBackView;
    private BitmapDescriptorFactory bitmapDescriptorFactory=new BitmapDescriptorFactory();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_plan);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //修改状态栏为文字为黑色
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        toolbar.setTitle("");
        // 设置主标题或者子标题是否应该被显示，隐藏掉toolbar自带的主标题和子标题
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mToolbarTitle = findViewById(R.id.toolbar_title);

        mBackView = LayoutInflater.from(this).inflate(R.layout.view_back, null, false);
        mTvBack = mBackView.findViewById(R.id.tv_back);

        toolbar.setNavigationIcon(bitmapDescriptorFactory.fromView(mBackView));
        //返回按钮点击事件
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

  /*      mSwitch = (Switch) findViewById(R.id.btn_layout);
        final TextView switchBtn_txtView = (TextView) findViewById(R.id.switchBtn_txtView);
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
                if (mFragment == planFragment2) {
                    switchFragment(planFragment1);
                } else {
                    switchFragment(planFragment2);
                }
            }
        });*/

        planFragment1 = new PlanFragment1();//英文
        planFragment2 = new PlanFragment2();//中文
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
//            mSwitch.setChecked(true);
            //初始情况 mFragment==null 可以设置中文
            if (mFragment==null||mFragment == planFragment1) {
                switchFragment(planFragment2);
            }
            //修改标题
            mToolbarTitle.setText("我的计划");
            mTvBack.setText("返回");
            this.invalidateOptionsMenu();
            toolbar.setNavigationIcon(bitmapDescriptorFactory.fromView(mBackView));

        } else {
            //修改标题
            mToolbarTitle.setText("My_plan");
            this.invalidateOptionsMenu();
            mTvBack.setText("Back");
            toolbar.setNavigationIcon(bitmapDescriptorFactory.fromView(mBackView));
//            mSwitch.setChecked(false);
            //初始情况 mFragment==null 可以设置英文
            if (mFragment==null||mFragment == planFragment2) {
                switchFragment(planFragment1);
            }
        }
    }

    private void switchFragment(Fragment fragment) {
        if (mFragment != fragment) {
            //防止横竖屏切换时出现重复加载的情况，修改为替换
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, fragment).commit();
            mFragment = fragment;
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
