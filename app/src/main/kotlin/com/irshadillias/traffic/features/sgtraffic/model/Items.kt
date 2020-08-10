package com.irshadillias.traffic.features.sgtraffic.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
/**
 * @author irshad illias
 * Model Class for capture time stamp and camera detail data from server
 */
data class Items (
		@Expose @SerializedName("timestamp") val timestamp : String,
		@Expose @SerializedName("cameras") val cameras : List<Cameras>
)