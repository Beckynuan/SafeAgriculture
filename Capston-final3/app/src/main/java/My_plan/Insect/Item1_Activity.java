package My_plan.Insect;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
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
import com.squareup.picasso.Picasso;

import My_plan.Snails.SnailsActivity;
import cz.msebera.android.httpclient.Header;

public class Item1_Activity extends AppCompatActivity {
    private ImageView image1;
    private ImageView image2;
    private ImageView image3;
    private ImageView image_che;
    private TextView text_title;
    private TextView text_body;
    private String text_url;
    private TextView description;

    AsyncHttpClient asyncHttpClient;

    String image_url = "https://raw.githubusercontent.com/Beckynuan/comp5703/main/resources/Dithane.jpeg";
    String text_url_eng = "https://raw.githubusercontent.com/Beckynuan/comp5703/main/resources/che_detail.txt";
    String text_url_cha = "https://raw.githubusercontent.com/jackie-1219/text/level/level.txt";

    private Toolbar toolbar;
    private TextView mTvBack;
    private TextView mToolbarTitle;
    private View mBackView;

    //View 转化Drawable辅助类
    private BitmapDescriptorFactory bitmapDescriptorFactory=new BitmapDescriptorFactory();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item1_);

        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        image_che = findViewById(R.id.item_image);
        text_title = findViewById(R.id.title);
        text_body = findViewById(R.id.body);
        description = findViewById(R.id.description);
        //image load
        Picasso.get().load(image_url).into(image_che);

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
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateLanguage(false);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        //导入菜单布局
        getMenuInflater().inflate(R.menu.main_total, menu);
        return true;
    }

    //menu已经准备好，可以修改文字
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (Constants.isChinese) {
            menu.getItem(0).setTitle("中文");
        } else {
            menu.getItem(0).setTitle("English");
        }

        return super.onPrepareOptionsMenu(menu);

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        //创建菜单项的点击事件
        switch (item.getItemId()) {
            case R.id.homeButton:
                Intent intent=new Intent(Item1_Activity.this, MainActivity.class);
                startActivity(intent);
                break;
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
            text_url = text_url_cha;
            description.setText("这种化学物质有毒！");
            executeLoopjCall();

            //修改标题
            mToolbarTitle.setText("购买");
            mTvBack.setText("返回");
            toolbar.setNavigationIcon(bitmapDescriptorFactory.fromView(mBackView));
        } else {
            text_url = text_url_eng;
            description.setText("This chemical is very toxic !");
            executeLoopjCall();

            //修改标题
            mToolbarTitle.setText("Purchase");
            mTvBack.setText("Back");
            toolbar.setNavigationIcon(bitmapDescriptorFactory.fromView(mBackView));

            }
    }

    public void executeLoopjCall() {
        new AsyncHttpClient().get(text_url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String str = new String(responseBody);
                text_body.setText(str);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                text_body.setText("Fail to get the content");
            }
        } );
    }

}