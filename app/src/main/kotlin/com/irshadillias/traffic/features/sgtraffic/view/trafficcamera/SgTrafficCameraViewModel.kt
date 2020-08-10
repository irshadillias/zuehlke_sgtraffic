package com.irshadillias.traffic.features.sgtraffic.view.trafficcamera

import androidx.lifecycle.MutableLiveData
import androidx.work.*
import com.irshadillias.traffic.core.platform.BaseViewModel
import com.irshadillias.traffic.features.sgtraffic.common.SgTrafficConstants
import com.irshadillias.traffic.features.sgtraffic.common.SgTrafficUtilities
import com.irshadillias.traffic.features.sgtraffic.workers.SyncDataWorker
import com.irshadillias.traffic.features.sgtraffic.model.GetTrafficResponse
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class SgTrafficCameraViewModel
@Inject constructor(private val getTrafficCamera: GetSgTrafficCamera, private val mWorkManager: WorkManager) : BaseViewModel() {
    var sgTraffic: MutableLiveData<GetTrafficResponse> = MutableLiveData()

    fun loadCameraDetail() {
        getTrafficCamera(requestParam(getCurrentTime())) { it.fold(::handleFailure, ::handleCovidDetail) }
    }

    private fun handleCovidDetail(cameraDetail: GetTrafficResponse) {
        this.sgTraffic.value = cameraDetail;
    }

    private fun getCurrentTime(): String{
        return SgTrafficUtilities.dateFormat(Calendar.getInstance().time)
    }

    private fun requestParam(timeValue: String): Map<String, String> {
        return mapOf("date_time" to timeValue)
    }

    /**
     * @author irshad illias
     * this repeated call implementation, with constrain
     * But interval will be 15 mint so this approach not suit for this requirement.
     */
    fun fetchData() {
        // Create Network constraint
        val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()
        val periodicSyncDataWork = PeriodicWorkRequest.Builder(SyncDataWorker::class.java, 1, TimeUnit.MINUTES)
                .addTag(SgTrafficConstants.TAG_SYNC_DATA)
                .setConstraints(constraints) // setting a backoff on case the work needs to retry
                .setBackoffCriteria(BackoffPolicy.LINEAR, PeriodicWorkRequest.MIN_BACKOFF_MILLIS, TimeUnit.MILLISECONDS)
                .build()
        mWorkManager.enqueueUniquePeriodicWork(
                SgTrafficConstants.SYNC_DATA_WORK_NAME,
                ExistingPeriodicWorkPolicy.KEEP,  //Existing Periodic Work policy
                periodicSyncDataWork //work request
        )
    }
}