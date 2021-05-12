package com.imageformgit;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.squareup.picasso.Picasso;

import cz.msebera.android.httpclient.Header;


public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView textView;
    private TextView textView_git;
    private String path_ima;
    private String path_text;


    // private File file = new File(uri.getPath("https://raw.githubusercontent.com/lipfiler/demo_1/main/1.jpeg"));
    //private String getFileContent(File file){


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.image);
        textView = findViewById(R.id.text);
        textView_git = findViewById(R.id.text_git);
//        textView_git = findViewById(R.id.text_git);
        path_ima = "https://raw.githubusercontent.com/lipfiler/demo_1/main/1.jpeg";
        Picasso.get().load(path_ima).into(imageView);
//
        path_text = "https://raw.githubusercontent.com/Beckynuan/comp5703/main/resources/file.txt";
        new AsyncHttpClient().get(path_text, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String str = new String(responseBody);
                textView_git.setText(str);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                textView.setText("Fail to get the content");
            }
        } );
    }


//            Document doc = Jsoup.connect("https://raw.githubusercontent.com/lipfiler/demo_2/master/instruction.txt\n").get();





    }







//




