package com.irshadillias.traffic.core.di

import com.irshadillias.traffic.TrafficSgApplication
import com.irshadillias.traffic.core.di.viewmodel.ViewModelModule
import com.irshadillias.traffic.core.navigation.RouteActivity
import com.irshadillias.traffic.features.sgtraffic.view.trafficcamera.SgTrafficCameraFragment
import com.irshadillias.traffic.features.sgtraffic.workers.SyncDataWorker
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(application: TrafficSgApplication)
    fun inject(routeActivity: RouteActivity)

    fun inject (sgTrafficFagment : SgTrafficCameraFragment)
    fun inject (syncDataWorker: SyncDataWorker)
}
