package Storage;

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

import Purchase.PurchaseActivity;
import cz.msebera.android.httpclient.Header;

public class StorageActivity extends AppCompatActivity {
    private ImageView mIvLanguage;
    private ImageView mIvFlash;

    AsyncHttpClient asyncHttpClient;
    private String t1_url;

    private TextView St1,St2;

    String Sstatement1 = "https://raw.githubusercontent.com/Beckynuan/comp5703/main/resources/SafeStorage/SafeStorage_statement.txt";

    String Sstatement2 = "https://raw.githubusercontent.com/Beckynuan/comp5703/main/resources/SafeStorage/SafeStorage_Cstatement.txt";

    YouTubePlayerView Splayer;

    private Toolbar toolbar;
    private TextView mTvBack;
    private TextView mToolbarTitle;
    private View mBackView;

    private BitmapDescriptorFactory bitmapDescriptorFactory=new BitmapDescriptorFactory();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        mIvLanguage = findViewById(R.id.Siv_language);
        mIvFlash = findViewById(R.id.Siv_flash);

        St1=(TextView)findViewById(R.id.STitle);
        St2=(TextView)findViewById(R.id.SStatement);

        mToolbarTitle = findViewById(R.id.toolbar_title);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("");
        mBackView = LayoutInflater.from(this).inflate(R.layout.view_back, null, false);

        mTvBack = mBackView.findViewById(R.id.tv_back);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Set whether the main title or subtitle should be displayed
        // hide the main title and subtitle that comes with the toolbar
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
                View view = LayoutInflater.from(StorageActivity.this).inflate(R.layout.pop_language, null, false);
                final PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                //Click on the outside and PopupWindow disappears.
                popupWindow.setOutsideTouchable(true);
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        bgAlpha(1.0f);//After disappearing, brightness is restored
                    }
                });
                TextView tvChinese = view.findViewById(R.id.tv_chinese);
                TextView tvEnglish = view.findViewById(R.id.tv_english);
                //Click Chinese button
                tvChinese.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Constants.isChinese=true;
                        updateLanguage(false);
                        popupWindow.dismiss();
                    }
                });
                //Click English button
                tvEnglish.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Constants.isChinese=false;
//                        item.setTitle("English");
                        updateLanguage(false);
                        popupWindow.dismiss();
                    }
                });
                bgAlpha(0.6f);///Display pop, background graying
                PopupWindowCompat.showAsDropDown(popupWindow, toolbar, -20, 0, Gravity.RIGHT);
            }
        });
        mIvFlash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reStartActivity();
            }
        });

        Splayer = (YouTubePlayerView) findViewById(R.id.Syoutube);

        //Determine whether the screen is horizontal or vertical
        //Get the configuration information of the setting
        Configuration mConfiguration = StorageActivity.this.getResources().getConfiguration();
        int ori = mConfiguration.orientation; //获取屏幕方向
        if (ori == mConfiguration.ORIENTATION_LANDSCAPE) {
            //Horizontal screen
            Splayer.enterFullScreen();
            //Hide full screen button
            Splayer.getPlayerUiController().showFullscreenButton(false);
            //Close title bar
            getSupportActionBar().hide();
        } else if (ori == mConfiguration.ORIENTATION_PORTRAIT) {
            //Portrait
            Splayer.exitFullScreen();
            //Show title bar
            getSupportActionBar().show();
            Splayer.getPlayerUiController().showFullscreenButton(true);
        }
        Splayer.addFullScreenListener(new YouTubePlayerFullScreenListener() {
            @Override
            public void onYouTubePlayerEnterFullScreen() {
                Log.d(TAG, "onYouTubePlayerEnterFullScreen: ");
                //If it is a vertical screen, then the fragment needs to be reset when the screen is small
                //Get the configuration information of the setting
                Configuration mConfiguration = StorageActivity.this.getResources().getConfiguration();
                //Get screen orientation
                int ori = mConfiguration.orientation;
                if (ori == mConfiguration.ORIENTATION_PORTRAIT) {
                    //Portrait
                    getSupportActionBar().hide();
                    Log.d(TAG, "onYouTubePlayerEnterFullScreen: 竖屏");
                    Splayer.enterFullScreen();
                    //getSupportFragmentManager().beginTransaction().hide(mFragment).commit();
//                    updateLanguage(true);
                }
            }

            @Override
            public void onYouTubePlayerExitFullScreen() {
                //If it is a vertical screen, then the fragment needs to be reset when the screen is small
                Log.d(TAG, "onYouTubePlayerExitFullScreen: ");
                //Get the configuration information of the setting
                Configuration mConfiguration = StorageActivity.this.getResources().getConfiguration();
                //Get screen orientation
                int ori = mConfiguration.orientation;
                if (ori == mConfiguration.ORIENTATION_PORTRAIT) {
                    //Portrait
                    getSupportActionBar().show();
                    Log.d(TAG, "onYouTubePlayerEnterFullScreen: 竖屏");
                    Splayer.exitFullScreen();
                    //getSupportFragmentManager().beginTransaction().show(mFragment).commit();
//                    updateLanguage(true);
                }
            }
        });
    }

    protected void onDestroy() {
        super.onDestroy();
        Splayer.release();
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
            t1_url=Sstatement2;
            executeLoopjCall();

            St1.setText("");
            St2.setText("等待中");
            mToolbarTitle.setText("储存");
            mTvBack.setText("返回");
            toolbar.setNavigationIcon(bitmapDescriptorFactory.fromView(mBackView));
        } else {
            t1_url=Sstatement1;
            executeLoopjCall();

            St1.setText("");
            St2.setText("Waiting ....");
            mToolbarTitle.setText("Storage");
            mTvBack.setText("Back");
            toolbar.setNavigationIcon(bitmapDescriptorFactory.fromView(mBackView));
        }
    }


    private static final String TAG = "jcy-PurchaseActivity";

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.d(TAG, "onConfigurationChanged: 横屏");
            Splayer.enterFullScreen();
            getSupportActionBar().hide();
            Splayer.getPlayerUiController().showFullscreenButton(false);
            Toast.makeText(StorageActivity.this, "Landscape Mode", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Log.d(TAG, "onConfigurationChanged: 竖屏");
            Splayer.exitFullScreen();
            getSupportActionBar().show();
            Splayer.getPlayerUiController().showFullscreenButton(true);
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
                St2.setText("Fail to get the content");
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

        St1.setText(title);
        St2.setText(content);
    }
}
