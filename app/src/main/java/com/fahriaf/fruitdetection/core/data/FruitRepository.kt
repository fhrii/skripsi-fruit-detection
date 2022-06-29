package com.fahriaf.fruitdetection.core.data

import android.graphics.Bitmap
import com.fahriaf.fruitdetection.core.data.remote.RemoteDataSource
import com.fahriaf.fruitdetection.core.data.remote.network.ApiResponse
import com.fahriaf.fruitdetection.core.domain.model.DetectedFruit
import com.fahriaf.fruitdetection.core.domain.repository.IFruitRepository
import com.fahriaf.fruitdetection.core.utils.FruitDataMapper
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FruitRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : IFruitRepository {
    override fun getFruitDetection(image: Bitmap): Flow<Resource<DetectedFruit>> {
        return flow {
            emit(Resource.Loading())
            when (val response = remoteDataSource.getFruitDetection(image).first()) {
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