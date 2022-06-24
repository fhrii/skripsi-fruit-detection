package com.fahriaf.fruitdetection.core.data.remote.network

import com.fahriaf.fruitdetection.core.data.remote.response.DetectedFruitResponse
import retrofit2.http.GET

interface ApiService {
    @GET("ccf6ada0-6f12-4d0a-bbf6-3d2cb2812775")
    suspend fun getFruitDetection(): DetectedFruitResponse
}