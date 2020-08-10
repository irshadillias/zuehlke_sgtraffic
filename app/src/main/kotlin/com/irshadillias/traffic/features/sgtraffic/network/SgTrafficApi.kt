package com.irshadillias.traffic.features.sgtraffic.network

import com.irshadillias.traffic.features.sgtraffic.model.GetTrafficResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

internal interface SgTrafficApi {

    companion object {
        private const val GETCASE = "traffic-images"
    }
    /*
     * Get service Api call for get SG camera details
     */
    @GET(GETCASE)
    fun getTrafiicCamera(@QueryMap(encoded = true) param: Map<String, String>): Call<GetTrafficResponse>
}