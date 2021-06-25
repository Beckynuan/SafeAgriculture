package Purchase;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.StyleSpan;
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
import androidx.fragment.app.Fragment;

import com.example.capston.BitmapDescriptorFactory;
import com.example.capston.Constants;
import com.example.capston.MainActivity;
import com.example.capston.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerFullScreenListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import My_plan.Insect.Item1_Activity;
import My_plan.Snails.SnailsActivity;
import cz.msebera.android.httpclient.Header;

public class PurchaseActivity extends AppCompatActivity {
    private ImageView mIvLanguage;
    private ImageView mIvFlash;

    AsyncHttpClient asyncHttpClient;
    private String t1_url;

    private TextView Ptitle;
    private TextView Pstatement;

    String Pstatement1 = "https://raw.githubusercontent.com/Beckynuan/comp5703/main/resources/PurchaseProduct/PurchaseProduct_statement.txt";

    String Pstatement2 = "https://raw.githubusercontent.com/Beckynuan/comp5703/main/resources/PurchaseProduct/PurchaseProduct_Cstatement.txt";

    YouTubePlayerView player;

    private Toolbar toolbar;
    private TextView mTvBack;
    private TextView mToolbarTitle;
    private View mBackView;

    private BitmapDescriptorFactory bitmapDescriptorFactory=new BitmapDescriptorFactory();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        mIvLanguage = findViewById(R.id.Piv_language);
        mIvFlash = findViewById(R.id.Piv_flash);

        Ptitle=(TextView)findViewById(R.id.PTitle);
        Pstatement=(TextView)findViewById(R.id.PStatement);

        mToolbarTitle = findViewById(R.id.toolbar_title);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("");
        mBackView = LayoutInflater.from(this).inflate(R.layout.view_back, null, false);

        mTvBack = mBackView.findViewById(R.id.tv_back);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Set whether the main title or subtitle should be displayed,
        // hide the main title and subtitle that comes with the toolbar
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(bitmapDescriptorFactory.fromView(mBackView));
        //Back button click event
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //Modify the status bar to black text
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        mIvFlash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });
        mIvLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = LayoutInflater.from(PurchaseActivity.this).inflate(R.layout.pop_language, null, false);
                final PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                //Click on the outside to disappear the popupwindow
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
//                        item.setTitle("中文");
                        updateLanguage(false);
                        popupWindow.dismiss();
                    }
                });
                //Click English option
                tvEnglish.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Constants.isChinese=false;
//                        item.setTitle("English");
                        updateLanguage(false);
                        popupWindow.dismiss();
                    }
                });
                bgAlpha(0.6f);//Show pop, background grayed out
                PopupWindowCompat.showAsDropDown(popupWindow, toolbar, -20, 0, Gravity.RIGHT);
            }
        });

        player = (YouTubePlayerView) findViewById(R.id.youtube);

        //Come in from the homepage to determine whether it is horizontal or vertical
        //Get the configuration information of the setting
        Configuration mConfiguration = PurchaseActivity.this.getResources().getConfiguration();
        //Get screen orientation
        int ori = mConfiguration.orientation;
        if (ori == mConfiguration.ORIENTATION_LANDSCAPE) {
            //Horizontal screen
            player.enterFullScreen();
            //Hide full screen button
            player.getPlayerUiController().showFullscreenButton(false);
            //Close title bar
            getSupportActionBar().hide();
        } else if (ori == mConfiguration.ORIENTATION_PORTRAIT) {
            //Portrait
            player.exitFullScreen();
            //Show title bar
            getSupportActionBar().show();
            player.getPlayerUiController().showFullscreenButton(true);
        }
        player.addFullScreenListener(new YouTubePlayerFullScreenListener() {
            @Override
            public void onYouTubePlayerEnterFullScreen() {
                Log.d(TAG, "onYouTubePlayerEnterFullScreen: ");
                //If it is a vertical screen, then the fragment needs to be reset when the screen is small
                //Get the configuration information of the setting
                Configuration mConfiguration = PurchaseActivity.this.getResources().getConfiguration();
                //Get screen orientation
                int ori = mConfiguration.orientation;
                if (ori == mConfiguration.ORIENTATION_PORTRAIT) {
                    //Portrait
                    getSupportActionBar().hide();
                    Log.d(TAG, "onYouTubePlayerEnterFullScreen: 竖屏");
                    player.enterFullScreen();
                }
            }

            @Override
            public void onYouTubePlayerExitFullScreen() {
                //If it is a vertical screen, then the fragment needs to be reset when the screen is small
                Log.d(TAG, "onYouTubePlayerExitFullScreen: ");
                //Get the configuration information of the setting
                Configuration mConfiguration = PurchaseActivity.this.getResources().getConfiguration();
                //Get screen orientation
                int ori = mConfiguration.orientation;
                if (ori == mConfiguration.ORIENTATION_PORTRAIT) {
                    //Portrait
                    getSupportActionBar().show();
                    Log.d(TAG, "onYouTubePlayerEnterFullScreen: 竖屏");
                    player.exitFullScreen();
                }
            }
        });
    }

    protected void onDestroy() {
        super.onDestroy();
        player.release();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateLanguage(false);
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

    //Unified modification and switching method between Chinese and English
    private void updateLanguage(boolean isReplace) {
        if (Constants.isChinese) {
            t1_url=Pstatement2;
            executeLoopjCall();
            Ptitle.setText("");
            Pstatement.setText("等待中");

            mToolbarTitle.setText("购买");
            mTvBack.setText("返回");
            toolbar.setNavigationIcon(bitmapDescriptorFactory.fromView(mBackView));
        } else {
            t1_url=Pstatement1;
            executeLoopjCall();
            Ptitle.setText("");
            Pstatement.setText("Waiting ....");

            mToolbarTitle.setText("Purchase");
            mTvBack.setText("Back");
            toolbar.setNavigationIcon(bitmapDescriptorFactory.fromView(mBackView));


        }
    }


    private static final String TAG = "jcy-PurchaseActivity";

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.d(TAG, "onConfigurationChanged: 横屏");
            player.enterFullScreen();
            getSupportActionBar().hide();
            player.getPlayerUiController().showFullscreenButton(false);
            Toast.makeText(PurchaseActivity.this, "Landscape Mode", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Log.d(TAG, "onConfigurationChanged: 竖屏");
            player.exitFullScreen();
            getSupportActionBar().show();
            player.getPlayerUiController().showFullscreenButton(true);
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
                Pstatement.setText("Fail to get the content");
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

        Ptitle.setText(title);
        Pstatement.setText(content);
    }

}
