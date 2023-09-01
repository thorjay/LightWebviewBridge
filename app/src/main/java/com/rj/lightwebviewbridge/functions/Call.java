package com.rj.lightwebviewbridge.functions;

import android.content.Intent;
import android.net.Uri;

import com.rj.bridge.context.BridgeContext;
import com.rj.bridge.core.NativeFunc;

/**
 * @decrpition 打电话
 * @author larryjay
*/
public class Call extends NativeFunc {

    public Call(BridgeContext context) {
        super(context);
    }

    public void call(String number) throws InterruptedException {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + number);
        intent.setData(data);
        context.getContext().startActivity(intent);
    }
}
