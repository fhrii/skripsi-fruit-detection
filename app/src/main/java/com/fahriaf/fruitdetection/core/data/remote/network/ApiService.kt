package com.fahriaf.fruitdetection.core.data.remote.network

import com.fahriaf.fruitdetection.core.data.remote.response.DetectedFruitResponse
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {
    @Multipart
    @POST("detector")
    suspend fun getFruitDetection(@Part image: MultipartBody.Part): DetectedFruitResponse
}