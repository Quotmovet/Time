package com.example.time.data.repositories.timescreen

import android.util.Log
import com.example.time.data.db.timescreen.converter.toModelFromDto
import com.example.time.data.dto.Resource
import com.example.time.data.dto.timescreen.ResponseWithResults
import com.example.time.data.dto.timescreen.TimeDataRequest
import com.example.time.data.network.NetworkClient
import com.example.time.domain.model.timescreen.TimeDataModel
import com.example.time.domain.repositories.timescreen.TimeDataRepository
import com.example.time.app.globalconstants.Constants.NO_INTERNET
import com.google.gson.JsonParseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class NetworkTimeDataRepositoryImpl @Inject constructor(private val networkClient: NetworkClient): TimeDataRepository {

    override suspend fun getTimeData(expression: String): Flow<Resource<List<TimeDataModel>>> = flow {
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
                            emit(Resource.Error(isFailed = true))
                        }
                        else -> {
                            emit(Resource.Error(isFailed = true))
                        }
                    }
                }
            }

        } catch (e: IOException) {
            Log.e("Network", "Network I/O failure for expression: $expression", e)
            emit(Resource.Error(isFailed = false))
        } catch (e: JsonParseException) {
            Log.e("Network", "Failed to parse response for: $expression", e)
            emit(Resource.Error(isFailed = true))
        } catch (e: HttpException) {
            Log.e("Network", "HTTP error (${e.code()}) for: $expression", e)
            emit(Resource.Error(isFailed = true))
        } catch (e: Exception) {
            Log.e("Network", "Unexpected exception for: $expression", e)
            emit(Resource.Error(isFailed = true))
        }
    }
}