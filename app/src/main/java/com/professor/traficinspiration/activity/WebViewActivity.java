package com.professor.traficinspiration.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.professor.traficinspiration.R;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        String baseUrl = "https://play.google.com/store/apps/details?id=";
        String packageName = "org.telegram.messenger";

        final WebView webView = (WebView) findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(baseUrl + packageName);

        // https://stackoverflow.com/questions/24451059/login-google-account-in-webview-using-account-manager-token
        // https://stackoverflow.com/questions/27586957/automatically-sign-into-google-account-on-android-webview

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    try {
//                        Thread.sleep(5000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    String url = webView.getUrl();
//                    Toast.makeText(WebViewActivity.this, url, Toast.LENGTH_SHORT).show();
//                }
//            }
//        }).start();

        String url = webView.getUrl();
        Toast.makeText(WebViewActivity.this, url, Toast.LENGTH_SHORT).show();

    }
}
