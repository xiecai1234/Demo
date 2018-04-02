package com.bjc.xcb.demo.activity;

import android.net.http.SslError;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bjc.xcb.demo.R;

public class WebviewActivity extends AppCompatActivity {
    private WebView webView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        //获取控件
        webView = (WebView) findViewById(R.id.webview);
        //设置加载网址
        webView.loadUrl("https://www.baidu.com/");
        //设置支持JavaScript
        webView.getSettings().setJavaScriptEnabled(true);
        //设置启用视meta标签的支持
        webView.getSettings().setUseWideViewPort(true);
        //不显示控制页面缩放的按钮
        webView.getSettings().setDisplayZoomControls(false);
        //使用内部缩放机制
        webView.getSettings().setBuiltInZoomControls(true);
        //设置支持缩放手势
        webView.getSettings().setSupportZoom(true);
        //设置适应设备屏幕
        webView.getSettings().setLoadWithOverviewMode(true);


        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
                // handler.cancel(); // handler.handleMessage(null); } });
                // onReceivedSslError为webView处理ssl证书设置
            }
        });
    }
}
