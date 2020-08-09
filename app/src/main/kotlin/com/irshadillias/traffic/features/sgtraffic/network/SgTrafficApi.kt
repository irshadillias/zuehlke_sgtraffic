package com.irshadillias.traffic.features.sgtraffic.network

import com.khalid.hamid.githubrepos.vo.lta.GetTrafficResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

internal interface SgTrafficApi {

    companion object {
        private const val GETCASE = "traffic-images"
    }

    @GET(GETCASE)
    fun getTrafiicCamera(@QueryMap(encoded = true) param: Map<String, String>): Call<GetTrafficResponse>
}