package com.rj.bridge.context

import android.content.Intent
import androidx.activity.result.ActivityResult

interface ActivityStarter {

    fun startActivity(intent: Intent, callback : (ActivityResult) -> Unit)
}