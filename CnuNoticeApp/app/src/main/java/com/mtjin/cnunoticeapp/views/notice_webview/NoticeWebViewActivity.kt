package com.mtjin.cnunoticeapp.views.notice_webview

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.mtjin.cnunoticeapp.R
import com.mtjin.cnunoticeapp.utils.constants.EXTRA_NOTICE_LINK
import kotlinx.android.synthetic.main.activity_notice_web_view.*


class NoticeWebViewActivity : AppCompatActivity() {
    private lateinit var mWebSettings: WebSettings
    private var url: String? = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice_web_view)
        processIntent()
        loadWebview()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun loadWebview() {
        wv_webview.webViewClient = WebViewClient() // 클릭시 새창 안뜨게
        mWebSettings = wv_webview.settings //세부 세팅 등록
        mWebSettings.javaScriptEnabled = true // 웹페이지 자바스클비트 허용 여부
        mWebSettings.setSupportMultipleWindows(false) // 새창 띄우기 허용 여부
        mWebSettings.javaScriptCanOpenWindowsAutomatically = false // 자바스크립트 새창 띄우기(멀티뷰) 허용 여부
        mWebSettings.loadWithOverviewMode = true // 메타태그 허용 여부
        mWebSettings.useWideViewPort = true // 화면 사이즈 맞추기 허용 여부
        mWebSettings.setSupportZoom(false) // 화면 줌 허용 여부
        mWebSettings.builtInZoomControls = false // 화면 확대 축소 허용 여부
        mWebSettings.cacheMode = WebSettings.LOAD_NO_CACHE // 브라우저 캐시 허용 여부
        mWebSettings.domStorageEnabled = true // 로컬저장소 허용 여부
        wv_webview.loadUrl(url.toString()) // 웹뷰에 표시할 웹사이트 주소, 웹뷰 시작
    }

    private fun processIntent() {
        url = intent.getStringExtra(EXTRA_NOTICE_LINK)
    }


}
