package com.irshadillias.traffic.features.sgtraffic.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
/**
 * @author irshad illias
 * Model Class for Image meta details
 */
data class Image_metadata (
		@Expose @SerializedName("height") val height : Int,
		@Expose @SerializedName("width") val width : Int,
		@Expose @SerializedName("md5") val md5 : String
)