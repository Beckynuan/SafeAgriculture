package Purchase;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.capston.R;
import com.example.capston.SSLSocketFactoryEx;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import java.security.KeyStore;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.conn.ssl.SSLSocketFactory;


public class PurChineseFra extends Fragment {
    private TextView t1,t2,t3,t4;
    AsyncHttpClient asyncHttpClient;
    //文本地址
    String Ptitle2 = "https://raw.githubusercontent.com/Jackie-1912/text/main/Ctitle.txt";
    String Pstatement2 = "https://raw.githubusercontent.com/Jackie-1912/text/main/Cstatement.txt";
    String Pchecklist2 = "https://raw.githubusercontent.com/Jackie-1912/text/main/Cchecklist.txt";
    String Pbody2 = "https://raw.githubusercontent.com/Jackie-1912/text/main/Cbody.txt";
    
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_pur_chinese,
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
        t1=(TextView) view.findViewById(R.id.CTitle1);
        t2=(TextView) view.findViewById(R.id.CStatement1);
        t3=(TextView) view.findViewById(R.id.CChecklist1);
        t4=(TextView) view.findViewById(R.id.Ctext1);

        executeLoopjCall();
    }
    private static final String TAG = "jcy-PurChineseFra";
    public void executeLoopjCall() {
        Log.d(TAG, "executeLoopjCall: 开始请求 "+Ptitle2);
        //开始执行get请求
        asyncHttpClient.get(Ptitle2,  new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                //请求失败
                Log.e(TAG, "onFailure: " + throwable);
                t1.setText("Fail to get the content");
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.i(TAG, "onSuccess: " + responseString);
                //请求成功后，需要切换主线程显示文本信息 runOnUiThread(切换主线程方法)
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        t1.setText(responseString);
                    }
                });
            }
        });

        asyncHttpClient.get(Pstatement2,  new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                //请求失败
                Log.e(TAG, "onFailure: " + throwable);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.i(TAG, "onSuccess: " + responseString);
                //请求成功后，需要切换主线程显示文本信息 runOnUiThread(切换主线程方法)
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        t2.setText(responseString);
                    }
                });
            }
        });

        asyncHttpClient.get(Pchecklist2,  new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                //请求失败
                Log.e(TAG, "onFailure: " + throwable);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.i(TAG, "onSuccess: " + responseString);
                //请求成功后，需要切换主线程显示文本信息 runOnUiThread(切换主线程方法)
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        t3.setText(responseString);
                    }
                });
            }
        });

        asyncHttpClient.get(Pbody2,  new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                //请求失败
                Log.e(TAG, "onFailure: " + throwable);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.i(TAG, "onSuccess: " + responseString);
                //请求成功后，需要切换主线程显示文本信息 runOnUiThread(切换主线程方法)
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        t4.setText(responseString);
                    }
                });
            }
        });


    }
}


