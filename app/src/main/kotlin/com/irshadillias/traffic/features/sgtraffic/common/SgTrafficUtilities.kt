package com.irshadillias.traffic.features.sgtraffic.common

import com.irshadillias.traffic.core.utilities.Constants
import java.text.SimpleDateFormat
import java.util.*

class SgTrafficUtilities {
    companion object {
        val sgDateFormat = SimpleDateFormat(Constants.API_CURRENT_TIME_FORMAT)
        fun dateFormat(currentTime : Date): String {
            return sgDateFormat.format(currentTime)
        }

        fun changeServerDateFormate (serverTime : String?): String{
            val serverTimeFor = SimpleDateFormat(SgTrafficConstants.SERVER_DATE_FORMAT, Locale.US)
            val clientTimeFor = SimpleDateFormat(SgTrafficConstants.UI_TIME_FORMATE, Locale.US)
            val mServerTime = serverTimeFor.parse(serverTime)
            return clientTimeFor.format(mServerTime)
        }
    }
}