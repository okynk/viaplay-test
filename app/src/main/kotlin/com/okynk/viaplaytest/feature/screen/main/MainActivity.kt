package com.okynk.viaplaytest.feature.screen.main

import android.os.Bundle
import com.okynk.viaplaytest.R
import com.okynk.viaplaytest.feature.base.BaseActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentViewWithNavGraph(navGraph = R.navigation.nav_main)
    }
}