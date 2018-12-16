package com.example.jesulonimi.finalocss;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


/**
 * A simple {@link Fragment} subclass.
 */
public class ArticleFragment extends Fragment {

    public ArticleFragment() {
    }

    WebView webView;
        public static ArticleFragment create(String link){
        Bundle args=new Bundle();
        args.putString("link",link);
        ArticleFragment fragment=new ArticleFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        webView=new WebView(getActivity());
        String link="";
        WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);



        webView.setWebViewClient(
                new WebViewClient(){
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                        view.loadUrl(request.toString());
                        return true;
                    }
                }
        );
        webView.loadUrl(getArguments().getString("link"));
        return webView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
