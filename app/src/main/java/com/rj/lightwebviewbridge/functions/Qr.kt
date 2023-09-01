package com.rj.lightwebviewbridge.functions

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.activity.result.ActivityResult
import androidx.annotation.RequiresApi
import com.blankj.utilcode.util.ConvertUtils
import com.blankj.utilcode.util.EncodeUtils
import com.king.camera.scan.CameraScan
import com.king.zxing.util.CodeUtils
import com.rj.bridge.context.ActivityStarter
import com.rj.bridge.context.BridgeContext
import com.rj.bridge.core.NativeFunc
import com.rj.lightwebviewbridge.qr.FullScreenQRCodeScanActivity

class Qr : NativeFunc {
    private lateinit var scanStarter : ActivityStarter

    constructor(context: BridgeContext) : super(context){
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun createQr(data : String){
        val bitmap = CodeUtils.createQRCode(data,600)
        val bytes = ConvertUtils.bitmap2Bytes(bitmap)
        val base64 = EncodeUtils.base64Encode2String(bytes)
        callbackToJS(base64)
    }

    fun scanQR(){
        toScan()
    }

    private fun toScan(){
        val intent = Intent(context.getContext(),FullScreenQRCodeScanActivity::class.java)
        scanStarter = context.getActivityStarter("scanQr")!!
        scanStarter.startActivity(intent){
                result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
                val result = intent!!.getStringExtra(CameraScan.SCAN_RESULT)
                callbackToJS(result)
            }
        }

    }

}