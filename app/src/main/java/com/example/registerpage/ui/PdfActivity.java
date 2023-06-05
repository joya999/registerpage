package com.example.registerpage.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.registerpage.R;
import com.example.registerpage.databinding.ActivityPdfBinding;

public class PdfActivity extends AppCompatActivity {


    private ActivityPdfBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPdfBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.webView.getSettings().setJavaScriptEnabled(true);
        binding.webView.loadUrl("https://docs.google.com/gview?embedded=true&url=" + getIntent().getStringExtra("url"));
        binding.webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Toast.makeText(PdfActivity.this, "started", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Toast.makeText(PdfActivity.this, "finished", Toast.LENGTH_SHORT).show();

            }
        });
    }
}