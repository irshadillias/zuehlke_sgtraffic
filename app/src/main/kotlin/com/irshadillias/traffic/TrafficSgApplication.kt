package com.irshadillias.traffic

import android.app.Application
import com.irshadillias.traffic.core.di.ApplicationComponent
import com.irshadillias.traffic.core.di.ApplicationModule
import com.irshadillias.traffic.core.di.DaggerApplicationComponent
import com.squareup.leakcanary.LeakCanary

class TrafficSgApplication : Application() {

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        this.injectMembers()
        this.initializeLeakDetection()
    }

    private fun injectMembers() = appComponent.inject(this)

    private fun initializeLeakDetection() {
        if (BuildConfig.DEBUG) LeakCanary.install(this)
    }
}
