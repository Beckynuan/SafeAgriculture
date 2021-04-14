package com.imageformgit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView textView;
    private Context context = this;
    private String path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.image);
        textView = findViewById(R.id.text);
        path = "https://raw.githubusercontent.com/lipfiler/demo_1/main/1.jpeg" ;
        //Picasso.get().load("https://raw.githubusercontent.com/lipfiler/demo_1/main/1.jpeg").into(imageView);
        Picasso.get().load(path).into(imageView);

    }
}




