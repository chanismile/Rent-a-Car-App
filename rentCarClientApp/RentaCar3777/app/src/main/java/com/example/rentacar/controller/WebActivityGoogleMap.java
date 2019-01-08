package com.example.rentacar.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.rentacar.R;
import com.example.rentacar.model.backend.Static;

public class WebActivityGoogleMap extends AppCompatActivity {

    private String urll= Static.url;
    private WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_google_map);
        webView = (WebView) findViewById(R.id.webView2);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://www.google.com/maps/place/"+urll);
    }



}
