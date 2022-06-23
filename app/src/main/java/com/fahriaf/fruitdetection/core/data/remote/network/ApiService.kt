package com.fahriaf.fruitdetection.core.data.remote.network

import com.fahriaf.fruitdetection.core.data.remote.response.DetectedFruitResponse
import retrofit2.http.GET

interface ApiService {
    @GET("0c708a07-0c5a-4b79-8557-52aa02f6391b")
    suspend fun getFruitDetection(): DetectedFruitResponse
}