package com.rj.bridge.config;

import android.content.Context;

import com.rj.util.BridgeConstant;
import com.rj.util.JacksonUtil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class BridgeConfigInitializer {

    public static BridgeConfig getConfig(Context context){
        try {
            InputStream in = context.getAssets().open(BridgeConstant.BRIDGE_FUNC_INFO);
            BridgeConfig config = JacksonUtil.getInstance().stream2Bean(in,BridgeConfig.class);
            return config;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
