package id.interview.newsapi.view.home

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import id.interview.newsapi.R
import id.interview.newsapi.repository.IView
import id.interview.newsapi.repository.ViewNetworkState
import id.interview.newsapi.repository.base.BaseActivity
import id.interview.newsapi.support.gone
import id.interview.newsapi.support.visible
import kotlinx.android.synthetic.main.activity_webview.*

class WebviewArticle : BaseActivity(), ViewNetworkState, IView {

    var urlID: String? = null
    var html: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)
        urlID = intent.getStringExtra("url_parse")
        initWebview()
        initView()
    }

    @SuppressLint("SetTextI18n")
    override fun initView() {
        back_btn_detail_article?.setOnClickListener {
            onBackPressed()
        }
    }
    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebview() {
        web_view?.settings?.javaScriptEnabled = true
        web_view?.webViewClient = object : WebViewClient() {

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                loading?.progress = 100
                loading?.gone()
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                loading?.progress = 0
                loading?.visible()
            }
        }
        web_view?.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                loading?.progress = newProgress
            }
        }

        if (html.isNullOrBlank()) {
            urlID?.let { web_view?.loadUrl(it) }
        } else {
            web_view?.loadDataWithBaseURL(null, html!!,"text/html","utf-8",null)
        }
    }

}