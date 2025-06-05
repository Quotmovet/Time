package com.example.time.data.network

import com.example.time.data.dto.Response

interface NetworkClient {
    suspend fun doRequest(dto: Any): Response
}