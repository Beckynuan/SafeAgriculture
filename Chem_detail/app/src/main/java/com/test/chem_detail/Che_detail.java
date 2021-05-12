package com.test.chem_detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.squareup.picasso.Picasso;

import cz.msebera.android.httpclient.Header;

public class Che_detail extends AppCompatActivity {

    private ImageView image1;
    private ImageView image2;
    private ImageView image3;
    private ImageView image_che;
    private TextView text_title;
    private TextView text_body;
    private String image_url;
    private String text_url_eng;
    private String text_url_fren;
    private String text_url;
    private Toolbar toolbar;
    private TextView lan_switch;
    private TextView description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(toolbar);


        image_url = "https://raw.githubusercontent.com/Beckynuan/comp5703/main/resources/Dithane.jpeg";
        text_url_eng = "https://raw.githubusercontent.com/Beckynuan/comp5703/main/resources/che_detail.txt";
        text_url_fren = "https://raw.githubusercontent.com/Beckynuan/comp5703/main/resources/che_detail_French.txt";
        text_url = text_url_eng;
        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        image_che = findViewById(R.id.item_image);
        text_title = findViewById(R.id.title);
        text_body = findViewById(R.id.body);
        lan_switch = findViewById(R.id.lan);
        description = findViewById(R.id.description);
        //image load
        Picasso.get().load(image_url).into(image_che);

        //description text load
        lan_switch();

        // language switch function.
        lan_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(Che_detail.this, v);
                popupMenu.getMenuInflater().inflate(R.menu.menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            // if english language is selected (default)
                            case R.id.Eng:
                                text_url = text_url_eng;
                                description.setText("This chemical is very toxic !");
                                lan_switch();
                                return true;

                            // if french language is selected
                            case R.id.Fren:

                                text_url = text_url_fren;
                                description.setText("Ce produit chimique est très toxique !");
                                lan_switch();
                                return true;

                            default:
                                return false;
                        }

                    }
                });
                popupMenu.show();
            }
        });
    }
    public void lan_switch() {
            // text detail loading.
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