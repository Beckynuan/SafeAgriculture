package My_plan.Insect;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.capston.R;
import com.example.capston.SSLSocketFactoryEx;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;
import com.squareup.picasso.Picasso;

import java.security.KeyStore;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.conn.ssl.SSLSocketFactory;

public class Item2_ChineseFra extends Fragment {

    private ImageView Cimage1;
    private ImageView Cimage2;
    private ImageView Cimage3;
    private ImageView Cimage_che;
    private TextView Ctext_title;
    private TextView Ctext_body;
    private TextView Clan_switch;

    AsyncHttpClient asyncHttpClient;

    String Cimage_url = "https://raw.githubusercontent.com/Beckynuan/comp5703/main/resources/Dithane.jpeg";
    String Ctext_url = "https://raw.githubusercontent.com/Jackie-1912/text/main/level.txt";


    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_item2__chinese_fra,
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

        Cimage1=view.findViewById(R.id.Cimage1);
        Cimage2=view.findViewById(R.id.Cimage2);
        Cimage3=view.findViewById(R.id.Cimage3);
        Cimage_che=view.findViewById(R.id.Citem_image);
        Ctext_title=view.findViewById(R.id.Ctitle);
        Ctext_body=view.findViewById(R.id.Cbody);
        Picasso.get().load(Cimage_url).into(Cimage_che);

        executeLoopjCall();
    }

    private static final String TAG = "jcy-PurEnglishFra";
    public void executeLoopjCall() {
        Log.d(TAG, "executeLoopjCall: 开始请求 "+Ctext_url);
        //开始执行get请求
        asyncHttpClient.get(Ctext_url,  new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Ctext_body.setText("Fail to get the content");
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
                        Ctext_body.setText(responseString);
                    }
                });
            }
        });
    }
}