package com.bc.lkh.news;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bc.lkh.news.R;

public class WebViewActivity extends Activity {
    LinearLayout linearLayout;
    ImageView imageView;
    TextView textView;
    WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.webview);
        linearLayout = findViewById(R.id.lin);
        imageView = findViewById(R.id.imageView2);
        textView = findViewById(R.id.textView);
        webView = findViewById(R.id.webview);
        //设置webview可以获取焦点
        webView.requestFocus();
        WebSettings webSettings = webView.getSettings();
        //设置兼容js语言
        webSettings.setJavaScriptEnabled(true);
        //设置支持缩放
        webSettings.setSupportZoom(true);
        //设置支持隐藏缩放按钮
        webSettings.setBuiltInZoomControls(true);
        //设置支持任意大小缩放
        webSettings.setUseWideViewPort(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override//处理网页中的子链接    true表示当前WebView直接加载子链接
            //false表示用代码块中的方式处理 没有的话默认用WebView
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return true;
            }

            @Override//监听页面加载的开始
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override//监听页面是否加载完毕
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

            }
        });
        webView.setWebChromeClient(new WebChromeClient() {
            @Override//监听网页加载的进度变化
            public void onProgressChanged(WebView view, int newProgress) {
            }

            @Override//接收当前加载网页的标题
            public void onReceivedTitle(WebView view, String title) {
                textView.setText(title);
            }

            @Override//接收当前加载网页的图标
            public void onReceivedIcon(WebView view, Bitmap icon) {
                imageView.setImageBitmap(icon);
            }

            @Override//处理JS对话框
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return true;
            }
        });

        Intent i = getIntent();
        webView.loadUrl(i.getStringExtra("url"));
    }
}
