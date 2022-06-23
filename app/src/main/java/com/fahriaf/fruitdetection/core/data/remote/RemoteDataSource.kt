package com.fahriaf.fruitdetection.core.data.remote

import android.util.Log
import com.fahriaf.fruitdetection.core.data.remote.network.ApiResponse
import com.fahriaf.fruitdetection.core.data.remote.network.ApiService
import com.fahriaf.fruitdetection.core.data.remote.response.DetectedFruitResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.log

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
    companion object {
        private const val TAG = "RemoteDataSource"
    }

    suspend fun getFruitDetection(): Flow<ApiResponse<DetectedFruitResponse>> {
        return flow {
            try {
                val response = apiService.getFruitDetection()
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