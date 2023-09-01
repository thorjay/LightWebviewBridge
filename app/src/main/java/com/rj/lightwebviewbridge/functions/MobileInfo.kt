package com.rj.lightwebviewbridge.functions

import com.blankj.utilcode.util.DeviceUtils
import com.rj.bridge.context.BridgeContext
import com.rj.bridge.core.NativeFunc

class MobileInfo(context: BridgeContext) : NativeFunc(context) {

    /**
     * 获取手机端的信息
     */
    fun getInfo(){
        val manufacturer = DeviceUtils.getManufacturer()
        val model = DeviceUtils.getModel()
        val sdkVersionName = DeviceUtils.getSDKVersionName()
        val data = "{\"设备厂商\":$manufacturer,\"设备型号\":$model,\"设备系统版本号\":$sdkVersionName}"
        callbackToJS(data)
    }

}