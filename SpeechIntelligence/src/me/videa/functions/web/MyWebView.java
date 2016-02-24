package me.videa.functions.web;

import me.videa.voice.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

public class MyWebView extends RelativeLayout{
	
	private LayoutInflater mInflater;
	private RelativeLayout mLayout;
	private WebView mWebView;

	@SuppressLint("SetJavaScriptEnabled") 
	public MyWebView(Context context) {
		super(context);
		mInflater = LayoutInflater.from(context);
		mLayout = (RelativeLayout) mInflater.inflate(
				R.layout.activity_web_main, null);
		mWebView = (WebView) mLayout.findViewById(R.id.webView);
		mWebView.setWebViewClient(new WebViewClient(){
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub
				view.loadUrl(url);
				return true;
			}
		});
		mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // TODO Auto-generated method stub
                if (newProgress == 100) {
                    // 网页加载完成

                } else {
                    // 加载中

                }

            }
        });
		mWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
		this.addView(mLayout);
	}
	
	public void loadPager(RequestOptions mOptions){		
		mWebView.loadUrl(mOptions.getmUrl());		
	}
	
	public boolean onBackPressed(){
		if(mWebView.canGoBack()){
			mWebView.goBack();
			return false;
		}
		return true;
	}

}
