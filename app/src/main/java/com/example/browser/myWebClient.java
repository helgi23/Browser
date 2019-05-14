package com.example.browser;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class myWebClient extends WebViewClient {


    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url){
        view.loadUrl(url);
        return true;
    }


}
