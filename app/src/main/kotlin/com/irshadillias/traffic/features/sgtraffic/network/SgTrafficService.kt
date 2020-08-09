package com.irshadillias.traffic.features.sgtraffic.network

import com.khalid.hamid.githubrepos.vo.lta.GetTrafficResponse
import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SgTrafficService
@Inject constructor(retrofit: Retrofit) : SgTrafficApi {
    private val sgTrafficApi by lazy { retrofit.create(SgTrafficApi::class.java) }
    override fun getTrafiicCamera(param: Map<String, String>): Call<GetTrafficResponse> = sgTrafficApi.getTrafiicCamera(param)
}
