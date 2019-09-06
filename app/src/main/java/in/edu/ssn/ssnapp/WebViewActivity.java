package in.edu.ssn.ssnapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import in.edu.ssn.ssnapp.utils.SharedPref;
import spencerstudios.com.bungeelib.Bungee;

public class WebViewActivity extends AppCompatActivity {

    WebView webView;
    ProgressBar pb_browser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        webView=findViewById(R.id.wv_library);
        pb_browser=findViewById(R.id.pb_browser);
        pb_browser.setMax(100);
    }

    @Override
    protected void onResume() {
        super.onResume();

        String url = SharedPref.getString(getApplicationContext(),"url");

        webView.loadUrl(url);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                pb_browser.setProgress(newProgress);
                if(newProgress==100)
                    pb_browser.setVisibility(View.GONE);
            }

            @Override
            public void onReceivedIcon(WebView view, Bitmap icon) {
                super.onReceivedIcon(view, icon);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        SharedPref.remove(getApplicationContext(),"url");
        Bungee.slideRight(WebViewActivity.this);
    }
}