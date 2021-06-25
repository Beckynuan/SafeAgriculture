package My_plan;

import android.annotation.SuppressLint;
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
import androidx.fragment.app.Fragment;

import com.example.capston.BitmapDescriptorFactory;
import com.example.capston.Constants;
import com.example.capston.MainActivity;
import com.example.capston.R;

import My_plan.Disease.DiseasesActivity;
import My_plan.Insect.InsectsActivity;
import My_plan.Rats.RatsActivity;
import My_plan.Snails.SnailsActivity;
import My_plan.Weeds.WeedsActivity;
import Purchase.PurchaseActivity;
import Storage.StorageActivity;

public class MyPlanActivity extends AppCompatActivity {
    private Button b1;
    private Button b2;
    private Button b3;
    private Button b4;
    private Button b5;

    private Toolbar toolbar;
    private TextView mToolbarTitle;
    private TextView mTvBack;
    private View mBackView;
    private BitmapDescriptorFactory bitmapDescriptorFactory=new BitmapDescriptorFactory();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_plan);

        b1 = (Button) findViewById(R.id.Pbutton);
        b2 = (Button) findViewById(R.id.Pbutton2);
        b3 = (Button) findViewById(R.id.Pbutton3);
        b4 = (Button) findViewById(R.id.Pbutton4);
        b5 = (Button) findViewById(R.id.Pbutton5);
        setListeners();
        
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Change the status bar to black text
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        toolbar.setTitle("");
        // Set whether the main title or subtitle should be displayed
        // hide the main title and subtitle that comes with the toolbar
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mToolbarTitle = findViewById(R.id.toolbar_title);

        mBackView = LayoutInflater.from(this).inflate(R.layout.view_back, null, false);
        mTvBack = mBackView.findViewById(R.id.tv_back);

        toolbar.setNavigationIcon(bitmapDescriptorFactory.fromView(mBackView));
        //Back button click event
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
        b1.setOnClickListener(onClick);
        b2.setOnClickListener(onClick);
        b3.setOnClickListener(onClick);
        b4.setOnClickListener(onClick);
        b5.setOnClickListener(onClick);
    }

    private class OnClick implements View.OnClickListener{
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()) {
                case R.id.Pbutton:
                    intent = new Intent(MyPlanActivity.this, InsectsActivity.class);
                    if(Constants.isChinese){
                        Toast.makeText(MyPlanActivity.this, "害虫被选中",
                                Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(MyPlanActivity.this, "Insect is choosen",
                                Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.Pbutton2:
                    intent = new Intent(MyPlanActivity.this, DiseasesActivity.class);
                    if(Constants.isChinese){
                        Toast.makeText(MyPlanActivity.this, "疾病被选中",
                                Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(MyPlanActivity.this, "Disease is choosen",
                                Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.Pbutton3:
                    intent = new Intent(MyPlanActivity.this, WeedsActivity.class);
                    if(Constants.isChinese){
                        Toast.makeText(MyPlanActivity.this, "杂草被选中",
                                Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(MyPlanActivity.this, "Weed is choosen",
                                Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.Pbutton4:
                    intent = new Intent(MyPlanActivity.this, RatsActivity.class);
                    if(Constants.isChinese){
                        Toast.makeText(MyPlanActivity.this, "鼠疫被选中",
                                Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(MyPlanActivity.this, "Rat is choosen",
                                Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.Pbutton5:
                    intent = new Intent(MyPlanActivity.this, SnailsActivity.class);
                    if(Constants.isChinese){
                        Toast.makeText(MyPlanActivity.this, "蜗牛被选中",
                                Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(MyPlanActivity.this, "Snail is choosen",
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
        //Import menu layout
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        //Create a click event for a menu item
        switch (item.getItemId()) {
            case R.id.language:
                View view = LayoutInflater.from(this).inflate(R.layout.pop_language,null,false);
                final PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                //Click outside, popupwindow can disappear
                popupWindow.setOutsideTouchable(true);
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        bgAlpha(1.0f);//After disappearing, restore brightness
                    }
                });
                TextView tvChinese = view.findViewById(R.id.tv_chinese);
                TextView tvEnglish = view.findViewById(R.id.tv_english);
                //Click Chinese option
                tvChinese.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Constants.isChinese=true;
                        item.setTitle("中文");
                        updateLanguage();
                        popupWindow.dismiss();
                    }
                });
                //Click English option
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
            b1.setText("害虫");
            b2.setText("疾病");
            b3.setText("杂草");
            b4.setText("鼠疫");
            b5.setText("蛇灾");

            //Edit title
            mToolbarTitle.setText("我的计划");
            mTvBack.setText("返回");
            this.invalidateOptionsMenu();
            toolbar.setNavigationIcon(bitmapDescriptorFactory.fromView(mBackView));

        } else {
            b1.setText("Insects");
            b2.setText("DISEASES");
            b3.setText("WEEDS");
            b4.setText("RATS");
            b5.setText("SNAILS");

            //Edit title
            mToolbarTitle.setText("My_plan");
            this.invalidateOptionsMenu();
            mTvBack.setText("Back");
            toolbar.setNavigationIcon(bitmapDescriptorFactory.fromView(mBackView));

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
