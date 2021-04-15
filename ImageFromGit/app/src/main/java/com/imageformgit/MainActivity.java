package com.imageformgit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView textView;
    private Context context = this;
    private String path;
   // private File file = new File(uri.getPath("https://raw.githubusercontent.com/lipfiler/demo_1/main/1.jpeg"));
    //private String getFileContent(File file){


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.image);
        textView = findViewById(R.id.text);
        path = "https://raw.githubusercontent.com/Beckynuan/comp5703/main/resources/1.jpeg?token=ARLDXLQ6BVT5BPZC3XME7NLAPBSIQ";
        //Picasso.get().load("https://raw.githubusercontent.com/lipfiler/demo_1/main/1.jpeg").into(imageView);
        Picasso.get().load(path).into(imageView);
        try {
            // Create a URL for the desired page
            URL urls = new URL("https://raw.githubusercontent.com/Beckynuan/comp5703/main/resources/file.txt?token=ARLDXLSYQJ2LZLNTOCE4N23APBQ5A");
            StringBuilder str = new StringBuilder();
            // launch task
            try {
                // Read all the text returned by the server
                BufferedReader in = new BufferedReader(new InputStreamReader(urls.openStream()));
                String line;
                while ((line = in.readLine()) != null)
                {
                    str.append(line).append(System.lineSeparator());
                }
                textView.setText(str);
                in.close();

            }
            catch (IOException e) {
                Toast.makeText(this,"cannot set content", Toast.LENGTH_SHORT).show();
            }
        }
        catch (MalformedURLException e) {
            Toast.makeText(this,"cannot get url", Toast.LENGTH_SHORT).show();
        }

        }
    }









