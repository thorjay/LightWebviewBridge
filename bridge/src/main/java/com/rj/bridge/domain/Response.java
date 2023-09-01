package com.rj.bridge.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @decrpition Native 功能返回数据给JS,a response send to JS by Native
 * eg:{"callbackKey":"callback123456","data":"data"}
 * @author larryjay
*/
public class Response {

    @JsonProperty("callbackKey")
    private String callbackKey;

    @JsonProperty("data")
    private String data;

    public void setCallbackKey(String callbackKey) {
        this.callbackKey = callbackKey;
    }

    public void setData(String data) {
        this.data = data;
    }

    @JsonCreator
    public Response(String callbackKey, String data) {
        this.callbackKey = callbackKey;
        this.data = data;
    }
}
