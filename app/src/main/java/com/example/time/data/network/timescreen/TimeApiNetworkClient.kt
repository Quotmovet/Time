package com.example.time.data.network.timescreen

import android.content.Context
import android.util.Log
import com.example.time.data.dto.Response
import com.example.time.data.dto.timescreen.ResponseWithResults
import com.example.time.data.dto.timescreen.TimeDataRequest
import com.example.time.data.network.NetworkClient
import com.example.time.presentation.common.util.Constants.BAD_REQUEST
import com.example.time.presentation.common.util.Constants.INTERNAL_SERVER_ERROR
import com.example.time.presentation.common.util.Constants.NO_INTERNET
import com.example.time.presentation.common.util.Constants.SUCCESS
import com.example.time.presentation.common.util.network.NetworkUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TimeApiNetworkClient @Inject constructor(
    private val timeApiService: TimeAPI,
    private val context: Context
) : NetworkClient {
    override suspend fun doRequest(dto: Any): Response {

        if (!NetworkUtils.isConnected(context)) {
            return Response().apply { resultCode = NO_INTERNET }
        }
        if (dto !is TimeDataRequest) {
            return Response().apply { resultCode = BAD_REQUEST }
        }

        return withContext(Dispatchers.IO) {
            try {
                val result = timeApiService.getTimeZoneData(dto.expression)
                Log.d("Network", "API raw response: $result")
                ResponseWithResults(listOf(result)).apply { resultCode = SUCCESS }

            } catch (e: Throwable) {
                Log.e("Network", "API request failed", e)
                Response().apply { resultCode = INTERNAL_SERVER_ERROR }
            }
        }
    }

}