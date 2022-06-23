package com.fahriaf.fruitdetection.core.domain.usecase

import android.graphics.Bitmap
import com.fahriaf.fruitdetection.core.domain.model.DetectedFruit
import com.fahriaf.fruitdetection.core.domain.repository.IFruitRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FruitInteractor @Inject constructor(private val fruitRepository: IFruitRepository) :
    FruitUseCase {
    override fun getFruitDetection(image: Bitmap) =
        fruitRepository.getFruitDetection(image)
}