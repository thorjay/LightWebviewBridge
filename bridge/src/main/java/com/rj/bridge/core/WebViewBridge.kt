package com.rj.bridge.core

import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.widget.Toast
import cn.hutool.core.util.ReflectUtil
import com.rj.bridge.config.BridgeConfigInitializer
import com.rj.bridge.context.BridgeContext
import com.rj.bridge.domain.BridgeFuntion
import com.rj.bridge.domain.Request
import com.rj.bridge.webview.BaseWebView
import com.rj.util.BridgeConstant
import com.rj.util.JacksonUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.lang.Exception

/**
 * @decrpition js调用native的统一桥梁
 * @author larryjay
*/
class WebViewBridge {

    private var functions: Map<String, BridgeFuntion>? = null

    private var iWebViewCallback: IWebViewDataHandler? = null

    private var context: BridgeContext? = null

    constructor(context: BridgeContext, callback: IWebViewDataHandler?) {
        this.context = context
        iWebViewCallback = callback
        functions = BridgeConfigInitializer.getConfig(context.getContext()).functions
    }

    /**
     * 封装js调用native功能的统一入口
     * @param requestStr 请求信息，包含funcId(功能唯一id),data(传入数据),callbackKey(回调唯一id)
     */
    @JavascriptInterface
    fun callFunc(requestStr : String) {
        val request = JacksonUtil.getInstance().strToBean(requestStr,Request::class.java)
        val funcId = request.funcId
        val data = request.data
        val callbackKey = request.callbackKey
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.Default){
                Timber.i("call执行在${Thread.currentThread().name}")
                call(funcId,data,callbackKey)
            }
        }
    }


    private fun call(funcId: String, data: String?, callbackKey: String?){
        try {
            val function = functions!![funcId]
            val className = function!!.className
            val methodName = function!!.methodName
            val clazz = Class.forName(className)
            val constructor = clazz.getConstructor(BridgeContext::class.java)
            val nativeFunc = constructor.newInstance(this.context)
            if (callbackKey?.isNotEmpty() == true) {
                val setIWebViewDataHandler = ReflectUtil.getMethod(clazz,"setiWebViewDataHandler",
                    IWebViewDataHandler::class.java)
                ReflectUtil.invoke<Unit>(nativeFunc,setIWebViewDataHandler,this.iWebViewCallback)
                val setCallback = ReflectUtil.getMethod(clazz,"setCallbackKey",String::class.java)
                ReflectUtil.invoke<Unit>(nativeFunc,setCallback,callbackKey)
            }
            if(data.isNullOrEmpty()){
                val method = clazz.getMethod(methodName)
                method.invoke(nativeFunc)
            }else{
                val method = clazz.getMethod(methodName, String::class.java)
                method.invoke(nativeFunc, data)
            }
        } catch (e: ReflectiveOperationException) {
            CoroutineScope(Dispatchers.Main).launch {
                Toast.makeText(this@WebViewBridge.context!!.getContext(),"获取原生功能方法报错，请检查对比配置信息或方法签名",Toast.LENGTH_LONG).show();
                Timber.e("call() throws ReflectiveOperationException: ${e.toString()}\ncause:${e.cause}")
            }
        }catch (e : Exception){
            Toast.makeText(this@WebViewBridge.context!!.getContext(),"获取原生功能方法报错，请检查配置信息",Toast.LENGTH_LONG).show();
            Timber.e("call() throws ReflectiveOperationException: ${e.stackTrace.toString()}\n" +
                    "cause:${e.cause}")
        }
    }

    companion object{
        fun initJsInterface(bridgeContext: BridgeContext,webView: BaseWebView){
            var webViewBridge = WebViewBridge(bridgeContext,webView)
            webView.addJavascriptInterface(webViewBridge, BridgeConstant.BRIDGE_NAME)
        }
    }

}