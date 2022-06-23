package com.fahriaf.fruitdetection.core.data

import android.graphics.Bitmap
import android.util.Log
import com.fahriaf.fruitdetection.core.data.remote.RemoteDataSource
import com.fahriaf.fruitdetection.core.data.remote.network.ApiResponse
import com.fahriaf.fruitdetection.core.domain.model.DetectedFruit
import com.fahriaf.fruitdetection.core.domain.repository.IFruitRepository
import com.fahriaf.fruitdetection.core.utils.FruitDataMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Singleton
import javax.xml.transform.TransformerFactoryConfigurationError

@Singleton
class FruitRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : IFruitRepository {
    override fun getFruitDetection(image: Bitmap): Flow<Resource<DetectedFruit>> {
        return flow {
            emit(Resource.Loading())
            val response = remoteDataSource.getFruitDetection().first()
            Log.d("asu", "getFruitDetection: $response")
            when (response) {
                is ApiResponse.Success -> emit(
                    Resource.Success(
                        FruitDataMapper.mapDetectedFruitResponseToDomain(
                            response.data
                        )
                    )
                )
                is ApiResponse.Empty -> emit(Resource.Success(DetectedFruit(null, listOf())))
                is ApiResponse.Error -> emit(Resource.Error(response.errorMessage))
            }
        }
    }
}