package com.test.pic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {


    private ImageView imageView;
    private String path = "https://raw.githubusercontent.com/Beckynuan/comp5703/16f3c71b6f529d8da5bad3da64ecc93e76f8909e/resources/1.jpeg?token=ARLDXLTTSBJXVIMAHZYZOHLAPJYIQ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageUrl);
        Picasso.get().load(path).into(imageView);

    }
}