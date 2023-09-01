package com.rj.bridge.webview

import android.content.Context
import android.util.AttributeSet
import android.webkit.WebView
import com.rj.bridge.core.IWebViewDataHandler
import com.rj.util.BridgeConstant
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BaseWebView: WebView, IWebViewDataHandler {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) :  super(context, attrs, defStyleAttr, defStyleRes)

    override fun callback(response: String?) {
        CoroutineScope(Dispatchers.Main).launch {
            this@BaseWebView.loadUrl("${BridgeConstant.BRIDGE_CALLBACK_JS}($response);")
        }
    }
}