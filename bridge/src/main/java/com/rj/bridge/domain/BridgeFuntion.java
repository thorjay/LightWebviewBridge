package com.rj.bridge.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * @decrpition 桥接原生方法属性
 * @author larryjay
*/
public class BridgeFuntion {

    @JsonProperty
    private String methodName;
    @JsonProperty
    private String className;

    public String getMethodName() {
        return methodName;
    }

    public String getClassName() {
        return className;
    }

    @Override
    public String toString() {
        return "BridgeFuntion{" +
                "methodName='" + methodName + '\'' +
                ", className='" + className + '\'' +
                '}';
    }
}
