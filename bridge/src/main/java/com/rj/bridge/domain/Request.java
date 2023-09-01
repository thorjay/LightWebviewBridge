package com.rj.bridge.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @decrpition a request by JS call Native function
 * eg:{"funcId":"func_name","data":"xxx","callbackKey":"callback123456"}
 * @author larryjay
*/
public class Request {

    @JsonProperty(value = "funcId",required = true)
    private String funcId;

    @JsonProperty(value = "data")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String data;

    @JsonProperty(value = "callbackKey")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String callbackKey;

    public String getFuncId() {
        return funcId;
    }

    public String getData() {
        return data;
    }

    public String getCallbackKey() {
        return callbackKey;
    }
}
