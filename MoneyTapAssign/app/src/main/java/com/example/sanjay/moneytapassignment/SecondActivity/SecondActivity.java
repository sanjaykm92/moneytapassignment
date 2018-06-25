package com.example.sanjay.moneytapassignment.SecondActivity;

import android.annotation.TargetApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.sanjay.moneytapassignment.R;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        String url = "";
        if(getIntent().getExtras()!=null) {
            url = getIntent().getExtras().getString("link").trim();
            url.replace(" ", "_");

        }
            WebView mWebview = (WebView) findViewById(R.id.activity_second_webview);
            mWebview.getSettings().setJavaScriptEnabled(true); // enable javascript

            mWebview.setWebViewClient(new WebViewClient() {
                @SuppressWarnings("deprecation")
                @Override
                public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                    Toast.makeText(SecondActivity.this, description, Toast.LENGTH_SHORT).show();
                }

                @TargetApi(android.os.Build.VERSION_CODES.M)
                @Override
                public void onReceivedError(WebView view, WebResourceRequest req, WebResourceError rerr) {
                    // Redirect to deprecated method, so you can use it in all SDK versions
                    onReceivedError(view, rerr.getErrorCode(), rerr.getDescription().toString(), req.getUrl().toString());
                }
            });

            mWebview.loadUrl("https://en.wikipedia.org/wiki/"+url);
    }
}
