package com.example.capston;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button PurchaseButton;
    private Button StorageButton;
    private Button MixingButton;
    private Button SprayingButton;
    private Button DisposalButton;
    private Button CleaningButton;
    private Button CareButton;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PurchaseButton=(Button)findViewById(R.id.button);
        StorageButton=(Button)findViewById(R.id.button2);
        MixingButton=(Button)findViewById(R.id.button3);
        SprayingButton=(Button)findViewById(R.id.button4);
        DisposalButton=(Button)findViewById(R.id.button5);
        CleaningButton=(Button)findViewById(R.id.button6);
        CareButton=(Button)findViewById(R.id.button7);
        setListeners();
    }

    private void setListeners() {
        OnClick onClick=new OnClick();
        PurchaseButton.setOnClickListener(onClick);
        StorageButton.setOnClickListener(onClick);
        MixingButton.setOnClickListener(onClick);
        SprayingButton.setOnClickListener(onClick);
        DisposalButton.setOnClickListener(onClick);
        CleaningButton.setOnClickListener(onClick);
        CareButton.setOnClickListener(onClick);
    }

    private class OnClick implements View.OnClickListener {
        public void onClick(View v) {
            Intent intent=null;
            switch(v.getId()){
                case R.id.button:
                    intent=new Intent(MainActivity.this, PurchaseActivity.class);
                    break;
                case R.id.button2:
                    intent=new Intent(MainActivity.this, StorageActivity.class);
                    break;
                case R.id.button3:
                    intent=new Intent(MainActivity.this, MixingActivity.class);
                    break;
                case R.id.button4:
                    intent=new Intent(MainActivity.this, SprayingActivity.class);
                    break;
                case R.id.button5:
                    intent=new Intent(MainActivity.this, DisposalActivity.class);
                    break;
                case R.id.button6:
                    intent=new Intent(MainActivity.this, CleaningActivity.class);
                    break;
                case R.id.button7:
                    intent=new Intent(MainActivity.this, CareActivity.class);
                    break;
            }
            startActivity(intent);
        }
    }
}