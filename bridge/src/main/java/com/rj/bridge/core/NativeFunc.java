package com.rj.bridge.core;

import com.rj.bridge.context.BridgeContext;
import com.rj.bridge.domain.Response;
import com.rj.util.JacksonUtil;

import org.json.JSONObject;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @decrpition 保存绑定native原生对象方法和callbackKey
 * @author larryjay
*/
public class NativeFunc {
    protected BridgeContext context;

    private IWebViewDataHandler iWebViewDataHandler;

    private ConcurrentHashMap<NativeFunc,String> callbackMap = new ConcurrentHashMap<>();

    public NativeFunc(BridgeContext context) {
        this.context = context;
    }

    public void setCallbackKey(String callbackKey){
        callbackMap.put(this,callbackKey);
    }

    /**
     * 传入webview对象，实现loadurl
     */
    public void setiWebViewDataHandler(IWebViewDataHandler iWebViewDataHandler){
        this.iWebViewDataHandler = iWebViewDataHandler;
    }

    public String getCallbackKey(){
        return callbackMap.get(this);
    }

    public void callbackToJS(String data){
        String callbackKey = getCallbackKey();
        Response response = new Response(callbackKey,data);
        iWebViewDataHandler.callback(fixJson(response));
    }


    /**
     * 解决json数据格式问题
     */
    private String fixJson(Response response){
        String responseStr = JacksonUtil.getInstance().beanToStr(response);
        //解决问题:Uncaught SyntaxError: Unexpected token o in JSON at position 1
        return JSONObject.quote(responseStr);
    }

}
