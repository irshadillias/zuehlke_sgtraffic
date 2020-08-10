package com.irshadillias.traffic.features.sgtraffic.model
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
/**
 * @author irshad illias
 * Model Class for capture api status data from server
 */
data class Api_info (
	@SerializedName ("status") @Expose val status : String

)