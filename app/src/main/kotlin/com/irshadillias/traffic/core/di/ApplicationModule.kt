package com.irshadillias.traffic.core.di

import android.content.Context
import androidx.work.WorkManager
import com.irshadillias.traffic.TrafficSgApplication
import com.irshadillias.traffic.BuildConfig
import com.irshadillias.traffic.core.utilities.Constants
import com.irshadillias.traffic.features.sgtraffic.network.SgTrafficRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: TrafficSgApplication) {

    @Provides @Singleton fun provideApplicationContext(): Context = application

    @Provides @Singleton fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(createClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @Provides @Singleton fun provideWorkManger(context: Context): WorkManager{
        return WorkManager.getInstance(context);
    }

    private fun createClient(): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
            okHttpClientBuilder.addInterceptor(loggingInterceptor)
        }
        return okHttpClientBuilder.build()
    }

    @Provides @Singleton fun provideTrafficRepository(dataSource: SgTrafficRepository.Network): SgTrafficRepository = dataSource
}
