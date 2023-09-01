package com.rj.util;

import com.rj.bridge.config.BridgeConfig;
import com.rj.bridge.domain.BridgeFuntion;
import com.rj.bridgeutil.BuildConfig;

import junit.framework.TestCase;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.hutool.core.io.FileUtil;

public class JacksonUtilTest extends TestCase {

    public void testStream2Bean() {
        try {
            BufferedInputStream in = FileUtil.getInputStream("/Users/larry/Documents/MyDocuments/MyLocalWorkSpaces/LightWebviewBridge/bridge/src/test/java/com/rj/util/bridgeConfig.json");
            BridgeConfig config = JacksonUtil.getInstance().stream2Bean(in, BridgeConfig.class);
            Map<String,BridgeFuntion> funtions = config.getFunctions();
            Set<String> sets =  funtions.keySet();
            Iterator<String> iterator = sets.iterator();
            while (iterator.hasNext()){
                String key = iterator.next();
                BridgeFuntion funtion = funtions.get(key);
                System.out.println(key + ":" + funtion.toString());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}