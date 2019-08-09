package com.py.news1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class detailed extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        Intent intent = getIntent();
        String link = intent.getStringExtra("link");
        WebView mywebview = (WebView) findViewById(R.id.web);
        mywebview.loadUrl(link);


    }
}
