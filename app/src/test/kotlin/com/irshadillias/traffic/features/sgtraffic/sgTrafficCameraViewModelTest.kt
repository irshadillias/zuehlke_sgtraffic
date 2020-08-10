package com.irshadillias.traffic.features.sgtraffic

import androidx.work.WorkManager
import com.irshadillias.traffic.AndroidTest
import com.irshadillias.traffic.core.functional.Either
import com.irshadillias.traffic.features.sgtraffic.view.trafficcamera.GetSgTrafficCamera
import com.irshadillias.traffic.features.sgtraffic.view.trafficcamera.SgTrafficCameraViewModel
import com.irshadillias.traffic.features.sgtraffic.model.GetTrafficResponse
import com.nhaarman.mockitokotlin2.given
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldEqualTo
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class sgTrafficCameraViewModelTest : AndroidTest() {
    private lateinit var sgTrafficCameraViewModel: SgTrafficCameraViewModel

    @Mock
    private lateinit var getCamaeraDetail: GetSgTrafficCamera

    @Mock
    private lateinit var mWorkManager: WorkManager

    @Before
    fun setUp() {
        sgTrafficCameraViewModel = SgTrafficCameraViewModel(getCamaeraDetail, mWorkManager)
    }

    @Test fun `loading Camera details should update live data`() {

        given { runBlocking { getCamaeraDetail.run(emptyMap()) } }.willReturn(Either.Right(GetTrafficResponse.empty()))
        sgTrafficCameraViewModel.sgTraffic.observeForever {
            with(it!!) {
                api_info.status shouldEqualTo ""
            }
        }
        runBlocking { sgTrafficCameraViewModel.loadCameraDetail() }
    }
}