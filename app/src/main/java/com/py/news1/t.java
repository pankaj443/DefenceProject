package com.py.news1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class t extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t);

        WebView mywebview = (WebView) findViewById(R.id.webv);
        mywebview.loadUrl("https://twitter.com/officialdgispr");
    }
}
