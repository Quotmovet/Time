package com.example.time.data.repositories.timescreen

import android.util.Log
import com.example.time.data.db.timescreen.converter.toModelFromDto
import com.example.time.data.dto.Resource
import com.example.time.data.dto.timescreen.ResponseWithResults
import com.example.time.data.dto.timescreen.TimeDataRequest
import com.example.time.data.network.NetworkClient
import com.example.time.domain.model.timescreen.TimeDataModel
import com.example.time.domain.repositories.timescreen.TimeDataRepository
import com.example.time.presentation.common.util.Constants.NO_INTERNET
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NetworkTimeDataRepositoryImpl @Inject constructor(
    private val networkClient: NetworkClient
) : TimeDataRepository {

    override suspend fun getTimeData(
        expression: String
    ) : Flow<Resource<List<TimeDataModel>>> = flow {
        try {

            val response = networkClient.doRequest(TimeDataRequest(expression))
            Log.d("Network", "Full response: $response")

            when (response) {
                is ResponseWithResults -> {
                    val data = response.results.map { it.toModelFromDto() }
                    emit(Resource.Success(data))
                }

                else -> {
                    when (response.resultCode) {
                        NO_INTERNET -> emit(Resource.Error(isFailed = false))
                        in 500..599 -> {
                            Log.e("Network", "Server error (${response.resultCode})")
                            emit(Resource.Error(isFailed = true))
                        }
                        else -> {
                            Log.e("Network", "Unknown error. Code: ${response.resultCode}")
                            emit(Resource.Error(isFailed = true))
                        }
                    }
                }

            }

        } catch (e: Exception) {
            Log.e("Network", "Request failed for expression: $expression", e)
            emit(Resource.Error(isFailed = true))
        }

    }
}