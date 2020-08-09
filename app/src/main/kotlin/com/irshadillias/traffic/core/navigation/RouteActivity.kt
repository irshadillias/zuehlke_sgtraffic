package com.irshadillias.traffic.core.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.irshadillias.traffic.TrafficSgApplication
import com.irshadillias.traffic.core.di.ApplicationComponent
import javax.inject.Inject

class RouteActivity : AppCompatActivity() {

    private val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (application as TrafficSgApplication).appComponent
    }

    @Inject internal lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        navigator.showMain(this)
    }
}
