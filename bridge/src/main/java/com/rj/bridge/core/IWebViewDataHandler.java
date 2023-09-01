package com.rj.bridge.core;

/**
 * @decrpition native和js的数据交换中间者
 * @author larryjay
*/
public interface IWebViewDataHandler {

    /**
     * native将回调数据发给web执行
    */
    void callback(String response);
}
