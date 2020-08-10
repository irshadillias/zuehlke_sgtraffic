package com.irshadillias.traffic.features.sgtraffic

import com.irshadillias.traffic.UnitTest
import com.irshadillias.traffic.core.functional.Either
import com.irshadillias.traffic.features.sgtraffic.network.SgTrafficRepository
import com.irshadillias.traffic.features.sgtraffic.view.trafficcamera.GetSgTrafficCamera
import com.irshadillias.traffic.features.sgtraffic.model.GetTrafficResponse
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class GetTrafficCameraImageTest: UnitTest(){

    private lateinit var getSgTrafficCamera : GetSgTrafficCamera

    @Mock
    private lateinit var sgTrafficRepository: SgTrafficRepository

    @Before
    fun setUp() {
        getSgTrafficCamera = GetSgTrafficCamera(sgTrafficRepository)
        given{ sgTrafficRepository.getTrafiicCamera(emptyMap<String, String>()) }.willReturn(Either.Right(GetTrafficResponse.empty()))
    }

    @Test
    fun `should get sgcamera data from repository`() {
        runBlocking { getSgTrafficCamera.run(emptyMap<String, String>()) }

        verify(sgTrafficRepository).getTrafiicCamera(emptyMap<String, String>())
        verifyNoMoreInteractions(sgTrafficRepository)
    }
}