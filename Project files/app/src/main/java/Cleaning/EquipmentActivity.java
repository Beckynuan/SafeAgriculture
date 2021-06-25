package Cleaning;

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
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.PopupWindowCompat;

import com.example.capston.BitmapDescriptorFactory;
import com.example.capston.Constants;
import com.example.capston.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerFullScreenListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import Mixing.MixingActivity;
import Purchase.PurchaseActivity;
import cz.msebera.android.httpclient.Header;

public class EquipmentActivity extends AppCompatActivity {
    private ImageView mIvLanguage;
    private ImageView mIvFlash;

    AsyncHttpClient asyncHttpClient;
    private String t1_url;

    private TextView Et1,Et2;
    String Estatement1 = "https://raw.githubusercontent.com/Beckynuan/comp5703/main/resources/CleaningEquipment/Cleanning_statement.txt";

    String Estatement2 = "https://raw.githubusercontent.com/Beckynuan/comp5703/main/resources/CleaningEquipment/Cleanning_Cstatement.txt";

    YouTubePlayerView Eplayer; // ssss

    private Toolbar toolbar;
    private TextView mTvBack;
    private TextView mToolbarTitle;
    private View mBackView;


    private BitmapDescriptorFactory bitmapDescriptorFactory=new BitmapDescriptorFactory();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment);

        mIvLanguage = findViewById(R.id.Eiv_language);
        mIvFlash = findViewById(R.id.Eiv_flash);

        Et1=(TextView)findViewById(R.id.ETitle);
        Et2=(TextView)findViewById(R.id.EStatement);

        mToolbarTitle = findViewById(R.id.toolbar_title);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("");
        mBackView = LayoutInflater.from(this).inflate(R.layout.view_back, null, false);

        mTvBack = mBackView.findViewById(R.id.tv_back);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Set whether the main title or subtitle should be displayed, hide the main title and subtitle that comes with the toolbar        // 设置主标题或者子标题是否应该被显示，隐藏掉toolbar自带的主标题和子标题
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(bitmapDescriptorFactory.fromView(mBackView));
        //Return to button click event
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //Change the status bar to black text
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        mIvLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = LayoutInflater.from(EquipmentActivity.this).inflate(R.layout.pop_language, null, false);
                final PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                //Click on the outside and PopupWindow disappears.
                popupWindow.setOutsideTouchable(true);
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        bgAlpha(1.0f);
                        //After disappearing, brightness is restored
                    }
                });
                TextView tvChinese = view.findViewById(R.id.tv_chinese);
                TextView tvEnglish = view.findViewById(R.id.tv_english);
                //click Chinese button
                tvChinese.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Constants.isChinese=true;
//                        item.setTitle("中文");
                        updateLanguage(false);
                        popupWindow.dismiss();
                    }
                });
                //click English button
                tvEnglish.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Constants.isChinese=false;
//                        item.setTitle("English");
                        updateLanguage(false);
                        popupWindow.dismiss();
                    }
                });
                bgAlpha(0.6f);//Display pop, background graying
                //positioning
                PopupWindowCompat.showAsDropDown(popupWindow, toolbar, -20, 0, Gravity.RIGHT);
            }
        });
        mIvFlash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reStartActivity();
            }
        });

        Eplayer = (YouTubePlayerView) findViewById(R.id.Eyoutube);

        //Determine whether the screen is horizontal or vertical
        Configuration mConfiguration = EquipmentActivity.this.getResources().getConfiguration();
        //Gets configuration information for the Settings
        int ori = mConfiguration.orientation;
        if (ori == mConfiguration.ORIENTATION_LANDSCAPE) {
            //Horizontal screen
            Eplayer.enterFullScreen();
            //Hide full screen button
            Eplayer.getPlayerUiController().showFullscreenButton(false);
            //Close title bar
            getSupportActionBar().hide();
        } else if (ori == mConfiguration.ORIENTATION_PORTRAIT) {
            //Portrait
            Eplayer.exitFullScreen();
            //Show title bar
            getSupportActionBar().show();
            Eplayer.getPlayerUiController().showFullscreenButton(true);
        }
        Eplayer.addFullScreenListener(new YouTubePlayerFullScreenListener() {
            @Override
            public void onYouTubePlayerEnterFullScreen() {
                Log.d(TAG, "onYouTubePlayerEnterFullScreen: ");
                //If it is a vertical screen, then the fragment needs to be reset when the screen is small
                Configuration mConfiguration = EquipmentActivity.this.getResources().getConfiguration();
                //Gets configuration information for the Settings
                int ori = mConfiguration.orientation;
                //Get the configuration information of the setting
                if (ori == mConfiguration.ORIENTATION_PORTRAIT) {
                    //Portrait
                    getSupportActionBar().hide();
                    Log.d(TAG, "onYouTubePlayerEnterFullScreen: 竖屏");
                    Eplayer.enterFullScreen();
                    //getSupportFragmentManager().beginTransaction().hide(mFragment).commit();
//                    updateLanguage(true);
                }
            }

            @Override
            public void onYouTubePlayerExitFullScreen() {
                //If it is a vertical screen, then the fragment needs to be reset when the screen is small
                Log.d(TAG, "onYouTubePlayerExitFullScreen: ");
                Configuration mConfiguration = EquipmentActivity.this.getResources().getConfiguration();
                //Gets configuration information for the Settings
                int ori = mConfiguration.orientation;
                //Get screen orientation
                if (ori == mConfiguration.ORIENTATION_PORTRAIT) {
                    //Portrait
                    getSupportActionBar().show();
                    Log.d(TAG, "onYouTubePlayerEnterFullScreen: 竖屏");
                    Eplayer.exitFullScreen();
                    //getSupportFragmentManager().beginTransaction().show(mFragment).commit();
//                    updateLanguage(true);
                }
            }
        });
    }

    protected void onDestroy() {
        super.onDestroy();
        Eplayer.release();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateLanguage(false);
    }

    private void reStartActivity() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    /**
     * Set the background transparency of the window
     *
     * @param f 0.0-1.0
     */
    private void bgAlpha(float f) {
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.alpha = f;
        getWindow().setAttributes(layoutParams);
    }

    //isForcibly forced conversion between Chinese and English
    private void updateLanguage(boolean isReplace) {
        if (Constants.isChinese) {
            t1_url=Estatement2;
            executeLoopjCall();

            Et1.setText("");
            Et2.setText("等待中");

            //Modify the title
            mToolbarTitle.setText("清洗设备");
            mTvBack.setText("返回");
            toolbar.setNavigationIcon(bitmapDescriptorFactory.fromView(mBackView));
        } else {
            t1_url=Estatement1;
            executeLoopjCall();

            Et1.setText("");
            Et2.setText("Waiting ....");

            mToolbarTitle.setText("Cleanning equipment");
            mTvBack.setText("Back");
            toolbar.setNavigationIcon(bitmapDescriptorFactory.fromView(mBackView));


        }
    }


    private static final String TAG = "jcy-PurchaseActivity";

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.d(TAG, "onConfigurationChanged: 横屏");
            Eplayer.enterFullScreen();
            getSupportActionBar().hide();
            Eplayer.getPlayerUiController().showFullscreenButton(false);
            Toast.makeText(EquipmentActivity.this, "Landscape Mode", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Log.d(TAG, "onConfigurationChanged: 竖屏");
            Eplayer.exitFullScreen();
            getSupportActionBar().show();
            Eplayer.getPlayerUiController().showFullscreenButton(true);
        }
    }

    public void executeLoopjCall() {
        new AsyncHttpClient().get(t1_url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String str = new String(responseBody);
                dataHandle(str);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Et2.setText("Fail to get the content");
            }
        } );
    }

    private void dataHandle(String data) {
        String title = "";
        String content = "";

        int index =  data.indexOf("\n");
        if(index>0){
            title=data.substring(0,index);
            content= data.substring(index);
        }

        Et1.setText(title);
        Et2.setText(content);
    }
}