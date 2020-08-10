package com.irshadillias.traffic.features.sgtraffic.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
/**
 * @author irshad illias
 * Model Class for capture data from server
 */
data class GetTrafficResponse (
	@Expose @SerializedName("items") val items : List<Items>,
	@Expose @SerializedName("api_info") val api_info : Api_info
){
	companion object {
		fun empty() = GetTrafficResponse(emptyList(), Api_info(""))
	}
}