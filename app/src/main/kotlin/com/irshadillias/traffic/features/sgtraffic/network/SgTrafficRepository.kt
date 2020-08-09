package com.irshadillias.traffic.features.sgtraffic.network

import com.irshadillias.traffic.core.exception.Failure
import com.irshadillias.traffic.core.functional.Either
import com.irshadillias.traffic.core.platform.NetworkHandler
import com.khalid.hamid.githubrepos.vo.lta.GetTrafficResponse
import retrofit2.Call
import javax.inject.Inject

interface SgTrafficRepository {
    fun getTrafiicCamera(param : Map<String, String>): Either<Failure, GetTrafficResponse>

    class Network
    @Inject constructor(private val networkHandler: NetworkHandler,
                        private val service: SgTrafficService) : SgTrafficRepository {

        override fun getTrafiicCamera(param : Map<String, String>): Either<Failure, GetTrafficResponse> {
            return when (networkHandler.isConnected) {
                true -> request(service.getTrafiicCamera(param), { it }, GetTrafficResponse.empty())
                false, null -> Either.Left(Failure.NetworkConnection)
            }
        }

        private fun <T, R> request(call: Call<T>, transform: (T) -> R, default: T): Either<Failure, R> {
            return try {
                val response = call.execute()
                when (response.isSuccessful) {
                    true -> Either.Right(transform((response.body() ?: default )))
                    false -> Either.Left(Failure.ServerError)
                }
            } catch (exception: Throwable) {
                Either.Left(Failure.ServerError)
            }
        }
    }
}
