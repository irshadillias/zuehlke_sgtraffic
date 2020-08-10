package com.irshadillias.traffic.features.sgtraffic.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
/**
 * @author irshad illias
 * Model Class for capture Location data from server
 */
data class Location (
		@Expose @SerializedName("latitude") val latitude : Double,
		@Expose @SerializedName("longitude") val longitude : Double
)