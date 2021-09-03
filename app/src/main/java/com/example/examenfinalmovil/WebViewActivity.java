package com.example.examenfinalmovil;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class WebViewActivity extends AppCompatActivity {

    WebView myVisorWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        Bundle bundle = this.getIntent().getExtras();
        Log.d("doi",bundle.getString("doi"));

        String url = bundle.getString("doi");
        myVisorWeb = (WebView) findViewById(R.id.visorWeb);
        final WebSettings ajust = myVisorWeb.getSettings();
        ajust.setJavaScriptEnabled(true);

        myVisorWeb.loadUrl(url);;
    }
}