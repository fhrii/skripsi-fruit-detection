package com.fahriaf.fruitdetection.core.data.remote

import android.graphics.Bitmap
import android.util.Log
import com.fahriaf.fruitdetection.core.utils.toByteArray
import com.fahriaf.fruitdetection.core.data.remote.network.ApiResponse
import com.fahriaf.fruitdetection.core.data.remote.network.ApiService
import com.fahriaf.fruitdetection.core.data.remote.response.DetectedFruitResponse
import com.fahriaf.fruitdetection.core.utils.DateHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
    companion object {
        private const val TAG = "RemoteDataSource"
    }

    suspend fun getFruitDetection(image: Bitmap): Flow<ApiResponse<DetectedFruitResponse>> {
        return flow {
            try {
                val imageByteArray = image.toByteArray()
                val requestBody = imageByteArray.toRequestBody("image/jpeg".toMediaTypeOrNull())
                val fileName = DateHelper.dateNowString()
                val body = MultipartBody.Part.createFormData("file", "$fileName.jpg", requestBody)
                val response = apiService.getFruitDetection(body)
                when (response.fruit) {
                    null -> emit(ApiResponse.Empty)
                    else -> emit((ApiResponse.Success(response)))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, "getFruitDetection: $e")
            }
        }.flowOn(Dispatchers.IO)
    }
}