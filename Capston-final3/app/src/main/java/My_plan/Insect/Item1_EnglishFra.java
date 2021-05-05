package My_plan.Insect;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.capston.R;
import com.example.capston.SSLSocketFactoryEx;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;
import com.squareup.picasso.Picasso;

import java.security.KeyStore;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.conn.ssl.SSLSocketFactory;

public class Item1_EnglishFra extends Fragment {

    private ImageView image1;
    private ImageView image2;
    private ImageView image3;
    private ImageView image_che;
    private TextView text_title;
    private TextView text_body;
    private TextView lan_switch;

    AsyncHttpClient asyncHttpClient;

    String image_url = "https://raw.githubusercontent.com/Beckynuan/comp5703/main/resources/Dithane.jpeg";
    String text_url = "https://raw.githubusercontent.com/Beckynuan/comp5703/main/resources/che_detail.txt";


    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_item1__english_fra,
                container,false);
        return view;
    }

    public void onViewCreated(View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        if(asyncHttpClient==null){
            //创建http客户端
            asyncHttpClient = new AsyncHttpClient();
            try {
                //在使用Https请求网络连接时，需要生成秘钥才能使用，但是对于github这种公网服务器，需要使用SSLSocketFactory屏蔽秘钥
                //trustStore创建这个对象用来拦截秘钥
                KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
                trustStore.load(null, null);
                SSLSocketFactoryEx sf = new SSLSocketFactoryEx(trustStore);
                //相当于 不在校验数据的合法性
                sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER); // 允许所有主机的验证
                asyncHttpClient.setSSLSocketFactory(sf);
            }catch (Exception e){
                Log.e(TAG, "Exception: " + e);
            }

        }

        image1=view.findViewById(R.id.image1);
        image2=view.findViewById(R.id.image2);
        image3=view.findViewById(R.id.image3);
        image_che=view.findViewById(R.id.item_image);
        text_title=view.findViewById(R.id.title);
        text_body=view.findViewById(R.id.body);
        Picasso.get().load(image_url).into(image_che);

        executeLoopjCall();
    }

    private static final String TAG = "jcy-PurEnglishFra";
    public void executeLoopjCall() {
        Log.d(TAG, "executeLoopjCall: 开始请求 "+text_url);
        //开始执行get请求
        asyncHttpClient.get(text_url,  new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        text_body.setText("Fail to get the content");
                    }
                });
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.i(TAG, "onSuccess: " + responseString);
                //请求成功后，需要切换主线程显示文本信息 runOnUiThread(切换主线程方法)
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        text_body.setText(responseString);
                    }
                });
            }
        });
    }
}