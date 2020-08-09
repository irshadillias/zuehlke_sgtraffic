package com.irshadillias.traffic.features.sgtraffic.view.trafficcamera

import com.irshadillias.traffic.core.interactor.UseCase
import com.irshadillias.traffic.features.sgtraffic.network.SgTrafficRepository
import com.khalid.hamid.githubrepos.vo.lta.GetTrafficResponse
import javax.inject.Inject

class GetSgTrafficCamera
@Inject constructor(private val sgRepository: SgTrafficRepository) : UseCase<GetTrafficResponse, Map<String, String>>() {

    override suspend fun run(params: Map<String, String>) = sgRepository.getTrafiicCamera(params)

    data class Params(val id: Map<String, String>)
}
