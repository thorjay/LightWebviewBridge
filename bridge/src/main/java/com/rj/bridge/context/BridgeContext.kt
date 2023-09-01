package com.rj.bridge.context

import androidx.fragment.app.FragmentActivity

class BridgeContext{

    private var context : FragmentActivity

    private var contractMap = mutableMapOf<String, ActivityStarter>()

    constructor(context : FragmentActivity){
        this.context = context
    }

    fun getContext():FragmentActivity{
        return this.context
    }

    fun addActivityStarter(key : String,starter: ActivityStarter){
        contractMap[key] = starter
    }

    fun getActivityStarter(key : String ) : ActivityStarter? {
        return contractMap[key]
    }
}