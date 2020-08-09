package com.irshadillias.traffic.core.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.irshadillias.traffic.features.sgtraffic.view.trafficcamera.SgTrafficCameraViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SgTrafficCameraViewModel::class)
    abstract fun bindsHomeViewModel(homeViewModel: SgTrafficCameraViewModel): ViewModel
}