package com.irshadillias.traffic.features.sgtraffic

import com.irshadillias.traffic.UnitTest
import com.irshadillias.traffic.core.functional.Either
import com.irshadillias.traffic.core.platform.NetworkHandler
import com.irshadillias.traffic.features.sgtraffic.network.SgTrafficRepository
import com.irshadillias.traffic.features.sgtraffic.network.SgTrafficService
import com.irshadillias.traffic.features.sgtraffic.model.GetTrafficResponse
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.verify
import org.amshove.kluent.shouldEqual
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import retrofit2.Call
import retrofit2.Response

class SgTrafficRepositoryTest : UnitTest() {
    private lateinit var networkRepository: SgTrafficRepository.Network

    @Mock
    private lateinit var networkHandler: NetworkHandler
    @Mock
    private lateinit var service: SgTrafficService

    @Mock
    private lateinit var trafficCall: Call<GetTrafficResponse>
    @Mock
    private lateinit var moviesResponse: Response<GetTrafficResponse>

    @Before
    fun setUp() {
        networkRepository = SgTrafficRepository.Network(networkHandler, service)
    }

    @Test
    fun `should return empty list by default`() {
        given { networkHandler.isConnected }.willReturn(true)
        given { moviesResponse.body() }.willReturn(null)
        given { moviesResponse.isSuccessful }.willReturn(true)
        given { trafficCall.execute() }.willReturn(moviesResponse)
        given { service.getTrafiicCamera(emptyMap()) }.willReturn(trafficCall)

        val sgCameraList = networkRepository.getTrafiicCamera(emptyMap())

        sgCameraList shouldEqual  Either.Right(GetTrafficResponse.empty())
        verify(service).getTrafiicCamera(emptyMap())
    }
}