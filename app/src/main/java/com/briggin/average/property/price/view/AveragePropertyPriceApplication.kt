package com.briggin.average.property.price.view

import android.app.Application
import com.briggin.average.property.price.koinModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AveragePropertyPriceApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AveragePropertyPriceApplication)
            modules(koinModule)
        }
    }
}