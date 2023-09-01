package com.rj.bridge.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rj.bridge.domain.BridgeFuntion;

import java.util.Map;

/**
 * @decrpition 配置文件
 * @author larryjay
*/
public class BridgeConfig {

    @JsonProperty("functions")
    private Map<String,BridgeFuntion> functions;

    public Map<String, BridgeFuntion> getFunctions() {
        return functions;
    }

}
