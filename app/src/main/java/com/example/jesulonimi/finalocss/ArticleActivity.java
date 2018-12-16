package com.example.jesulonimi.finalocss;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ArticleActivity extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction ft=fragmentManager.beginTransaction();
        setTitle(getIntent().getStringExtra("title"));
        ArticleFragment fragment=ArticleFragment.create(getIntent().getStringExtra("link"));
ArticleFragment fragment1=new ArticleFragment();
Fragment f=new Fragment();
                    ft.replace(android.R.id.content, fragment).commit();

    }
}
