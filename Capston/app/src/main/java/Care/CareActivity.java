package Care;

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

import Cleaning.EquipmentActivity;
import cz.msebera.android.httpclient.Header;

public class CareActivity extends AppCompatActivity {
    AsyncHttpClient asyncHttpClient;
    private String t1_url,t2_url;

    private TextView Ct1,Ct2;
    String Ctitle1 = "https://raw.githubusercontent.com/Beckynuan/comp5703/main/resources/SafeCare/Care_title.txt";
    String Cstatement1 = "https://raw.githubusercontent.com/Beckynuan/comp5703/main/resources/SafeCare/Care_statement.txt";

    String Ctitle2 = "https://raw.githubusercontent.com/Beckynuan/comp5703/main/resources/SafeCare/Care_Ctitle.txt";
    String Cstatement2 = "https://raw.githubusercontent.com/Beckynuan/comp5703/main/resources/SafeCare/Care_Cstatement.txt";

    YouTubePlayerView Cplayer;

    private Toolbar toolbar;
    private TextView mTvBack;
    private TextView mToolbarTitle;
    private View mBackView;

    //View 转化Drawable辅助类..
    private BitmapDescriptorFactory bitmapDescriptorFactory=new BitmapDescriptorFactory();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_care);

        Ct1=(TextView)findViewById(R.id.CTitle);
        Ct2=(TextView)findViewById(R.id.CStatement);

        mToolbarTitle = findViewById(R.id.toolbar_title);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("");
        mBackView = LayoutInflater.from(this).inflate(R.layout.view_back, null, false);

        mTvBack = mBackView.findViewById(R.id.tv_back);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // 设置主标题或者子标题是否应该被显示，隐藏掉toolbar自带的主标题和子标题
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(bitmapDescriptorFactory.fromView(mBackView));
        //返回按钮点击事件
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //修改状态栏为文字为黑色
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        Cplayer = (YouTubePlayerView) findViewById(R.id.Cyoutube);

        //从首页进来判断是横屏还是竖屏
        Configuration mConfiguration = CareActivity.this.getResources().getConfiguration(); //获取设置的配置信息
        int ori = mConfiguration.orientation; //获取屏幕方向
        if (ori == mConfiguration.ORIENTATION_LANDSCAPE) {
            //横屏
            Cplayer.enterFullScreen();
            //隐藏全屏按钮
            Cplayer.getPlayerUiController().showFullscreenButton(false);
            //关闭标题栏
            getSupportActionBar().hide();
        } else if (ori == mConfiguration.ORIENTATION_PORTRAIT) {
            //竖屏
            Cplayer.exitFullScreen();
            //显示标题栏
            getSupportActionBar().show();
            Cplayer.getPlayerUiController().showFullscreenButton(true);
        }
        Cplayer.addFullScreenListener(new YouTubePlayerFullScreenListener() {
            @Override
            public void onYouTubePlayerEnterFullScreen() {
                Log.d(TAG, "onYouTubePlayerEnterFullScreen: ");
                //如果为竖屏那么在小屏幕的时候需要重新设置fragment
                Configuration mConfiguration = CareActivity.this.getResources().getConfiguration(); //获取设置的配置信息
                int ori = mConfiguration.orientation; //获取屏幕方向
                if (ori == mConfiguration.ORIENTATION_PORTRAIT) {
                    //竖屏
                    getSupportActionBar().hide();
                    Log.d(TAG, "onYouTubePlayerEnterFullScreen: 竖屏");
                    Cplayer.enterFullScreen();
                    //getSupportFragmentManager().beginTransaction().hide(mFragment).commit();
//                    updateLanguage(true);
                }
            }

            @Override
            public void onYouTubePlayerExitFullScreen() {
                //如果为竖屏那么在小屏幕的时候需要重新设置fragment
                Log.d(TAG, "onYouTubePlayerExitFullScreen: ");
                Configuration mConfiguration = CareActivity.this.getResources().getConfiguration(); //获取设置的配置信息
                int ori = mConfiguration.orientation; //获取屏幕方向
                if (ori == mConfiguration.ORIENTATION_PORTRAIT) {
                    //竖屏
                    getSupportActionBar().show();
                    Log.d(TAG, "onYouTubePlayerEnterFullScreen: 竖屏");
                    Cplayer.exitFullScreen();
                    //getSupportFragmentManager().beginTransaction().show(mFragment).commit();
//                    updateLanguage(true);
                }
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        updateLanguage(false);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        //导入菜单布局
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        //创建菜单项的点击事件
        switch (item.getItemId()) {
            case R.id.language:
                View view = LayoutInflater.from(this).inflate(R.layout.pop_language, null, false);
                final PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
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
                        updateLanguage(false);
                        popupWindow.dismiss();
                    }
                });
                //点击英文
                tvEnglish.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Constants.isChinese=false;
                        item.setTitle("English");
                        updateLanguage(false);
                        popupWindow.dismiss();
                    }
                });
                bgAlpha(0.6f);//显示pop,背景变灰
                //定位
                PopupWindowCompat.showAsDropDown(popupWindow, toolbar, -20, 0, Gravity.RIGHT);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 设置窗口的背景透明度
     *
     * @param f 0.0-1.0
     */
    private void bgAlpha(float f) {
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.alpha = f;
        getWindow().setAttributes(layoutParams);
    }

    //统一修改切换中英文方法 isForcibly 强制转换
    private void updateLanguage(boolean isReplace) {
        if (Constants.isChinese) {
            t1_url=Ctitle2;
            t2_url=Cstatement2;
            executeLoopjCall();

            Ct1.setText("等待中");
            //修改标题
            mToolbarTitle.setText("自我防护");
            mTvBack.setText("返回");
            toolbar.setNavigationIcon(bitmapDescriptorFactory.fromView(mBackView));
        } else {
            t1_url=Ctitle1;
            t2_url=Cstatement1;
            executeLoopjCall();

            mToolbarTitle.setText("Self Care");
            mTvBack.setText("Back");
            toolbar.setNavigationIcon(bitmapDescriptorFactory.fromView(mBackView));


        }
    }


    private static final String TAG = "jcy-PurchaseActivity";

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.d(TAG, "onConfigurationChanged: 横屏");
            Cplayer.enterFullScreen();
            getSupportActionBar().hide();
            Cplayer.getPlayerUiController().showFullscreenButton(false);
            Toast.makeText(CareActivity.this, "Landscape Mode", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Log.d(TAG, "onConfigurationChanged: 竖屏");
            Cplayer.exitFullScreen();
            getSupportActionBar().show();
            Cplayer.getPlayerUiController().showFullscreenButton(true);
        }
    }

    public void executeLoopjCall() {
        new AsyncHttpClient().get(t1_url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String str = new String(responseBody);
                Ct1.setText(str);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Ct1.setText("Fail to get the content");
            }
        } );

        new AsyncHttpClient().get(t2_url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String str = new String(responseBody);
                Ct2.setText(str);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            }
        } );
    }
}