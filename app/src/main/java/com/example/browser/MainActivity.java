package com.example.browser;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;


public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    EditText autoCompleteTextView;
    WebView webView;
    ImageButton sendButton;
    ImageButton forwardButton;
    ImageButton backButton;
    ImageButton refreshButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        autoCompleteTextView = (EditText) findViewById(R.id.autoCompleteTextView);
        webView = (WebView) findViewById(R.id.webView);
        sendButton = (ImageButton) findViewById(R.id.sendButton);
        forwardButton = (ImageButton) findViewById(R.id.forwardButton);
        backButton = (ImageButton) findViewById(R.id.backButton);
        refreshButton = (ImageButton) findViewById(R.id.refreshButton);

        webView.setWebViewClient(new myWebClient());

        webView.setWebChromeClient(new WebChromeClient(){

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                progressBar.setProgress(newProgress);
                if(newProgress==100)
                    progressBar.setVisibility(View.GONE);
                else
                    progressBar.setVisibility(View.VISIBLE);
            }
        });
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

        webView.loadUrl("http://www.google.com");

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = autoCompleteTextView.getText().toString();

                if (!url.startsWith("http://")){
                    url = "http://" + url;
                }
                webView.loadUrl(url);

                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(webView.getWindowToken(), 0);


                forwardButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (webView.canGoForward()){
                            webView.goForward();
                        }
                    }
                });

                backButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (webView.canGoBack()){
                            webView.goBack();
                        }
                    }
                });

                refreshButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        webView.reload();
                    }
                });
            }
        });

    }
}
