package com.rj.lightwebviewbridge.ui

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import com.rj.bridge.context.BridgeContext
import com.rj.bridge.core.WebViewBridge
import com.rj.lightwebviewbridge.databinding.FragmentWebBinding
import com.rj.lightwebviewbridge.qr.ScanObserver
import com.rj.bridge.webview.BaseWebView
import com.rj.lightwebviewbridge.BuildConfig

class WebFragment : Fragment() {

    private lateinit var binding : FragmentWebBinding

    private lateinit var mWebview : BaseWebView

    private lateinit var bridgeContext : BridgeContext

    companion object {
        fun newInstance() = WebFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bridgeContext = BridgeContext(this.requireActivity())

        val scanObserver = ScanObserver(this.requireActivity().activityResultRegistry)
        lifecycle.addObserver(scanObserver)
        bridgeContext.addActivityStarter("scanQr",scanObserver)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWebBinding.inflate(inflater,container,false)
        mWebview = binding.webview
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initWeviewSetting()
        WebViewBridge.initJsInterface(bridgeContext,mWebview)
        mWebview.loadUrl("file:///android_asset/web/dist/index.html")
    }

    private fun initWeviewSetting(){
        val settings = mWebview.settings
        settings.javaScriptEnabled = true
        settings.cacheMode = WebSettings.LOAD_NO_CACHE
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT && BuildConfig.DEBUG){
            WebView.setWebContentsDebuggingEnabled(true)
        }
    }

    override fun onResume() {
        super.onResume()
    }


}