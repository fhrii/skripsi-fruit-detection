package com.fahriaf.fruitdetection.core.domain.repository

import android.graphics.Bitmap
import com.fahriaf.fruitdetection.core.data.Resource
import com.fahriaf.fruitdetection.core.domain.model.DetectedFruit
import kotlinx.coroutines.flow.Flow

interface IFruitRepository {
    fun getFruitDetection(image: Bitmap): Flow<Resource<DetectedFruit>>
}