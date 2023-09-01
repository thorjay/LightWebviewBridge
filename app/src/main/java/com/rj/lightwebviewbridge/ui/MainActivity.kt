package com.rj.lightwebviewbridge.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rj.lightwebviewbridge.R


class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.name

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, WebFragment.newInstance())
                .commitNow()
        }
    }
}