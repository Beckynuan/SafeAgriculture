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
    private ImageView mIvLanguage;
    private ImageView mIvHome;

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
    String text_url_eng = "https://raw.githubusercontent.com/Beckynuan/comp5703/main/resources/MyPlan/MyPlan_Eng.txt";
    String text_url_cha = "https://raw.githubusercontent.com/Beckynuan/comp5703/main/resources/MyPlan/Myplan_Chinese.txt";

    private Toolbar toolbar;
    private TextView mTvBack;
    private TextView mToolbarTitle;
    private View mBackView;
    private BitmapDescriptorFactory bitmapDescriptorFactory=new BitmapDescriptorFactory();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item1_);

        mIvLanguage = findViewById(R.id.iv_language);
        mIvHome = findViewById(R.id.iv_home);

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
        //Change the status bar to black text
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        mIvLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = LayoutInflater.from(Item1_Activity.this).inflate(R.layout.pop_language, null, false);
                final PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
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
                        Constants.isChinese = true;
                        updateLanguage(false);
                        popupWindow.dismiss();
                    }
                });
                //Click English option
                tvEnglish.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Constants.isChinese = false;
                        updateLanguage(false);
                        popupWindow.dismiss();

                    }
                });
                bgAlpha(0.6f);//Show pop, background grayed out
                PopupWindowCompat.showAsDropDown(popupWindow, toolbar, -20, 0, Gravity.RIGHT);
            }
        });
        mIvHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Item1_Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });

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
            text_url = text_url_cha;
            description.setText("这种化学物质有毒！");
            executeLoopjCall();

            //Edit title
            mToolbarTitle.setText("二烷");
            mTvBack.setText("返回");
            toolbar.setNavigationIcon(bitmapDescriptorFactory.fromView(mBackView));
        } else {
            text_url = text_url_eng;
            description.setText("This chemical is very toxic !");
            executeLoopjCall();

            //Edit title
            mToolbarTitle.setText("Dithane");
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