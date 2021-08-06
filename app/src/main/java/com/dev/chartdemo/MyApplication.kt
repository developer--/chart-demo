package com.dev.chartdemo

import android.app.Application
import com.dev.chartdemo.common.di.SampleDiProvider

/**
 * @Author: Jemo Mgebrishvili
 * @Date: 06.08.21
 */
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        SampleDiProvider.inject()
    }
}