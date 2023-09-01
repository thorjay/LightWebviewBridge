package com.rj.lightwebviewbridge.qr

import android.content.Intent
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.rj.bridge.context.ActivityStarter

class ScanObserver(private val registry: ActivityResultRegistry) : DefaultLifecycleObserver,
    ActivityStarter {

    lateinit var startScan : ActivityResultLauncher<Intent>

    lateinit var callback : (ActivityResult) -> Unit

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        startScan = registry.register("scan",owner,
            ActivityResultContracts.StartActivityForResult()){
                callback(it)
        }
    }

    override fun startActivity(intent: Intent, callback : (ActivityResult) -> Unit){
        this.callback = callback
        startScan.launch(intent)
    }
}