package com.fahriaf.fruitdetection.core.utils

import com.fahriaf.fruitdetection.core.data.remote.response.DetectedFruitResponse
import com.fahriaf.fruitdetection.core.data.remote.response.FruitResponse
import com.fahriaf.fruitdetection.core.domain.model.DetectedFruit
import com.fahriaf.fruitdetection.core.domain.model.Fruit

object FruitDataMapper {
    fun mapDetectedFruitResponseToDomain(detectedFruit: DetectedFruitResponse): DetectedFruit {
        return DetectedFruit(
            fruit = mapFruitResponseToDomain(detectedFruit.fruit),
            otherDetection = detectedFruit.otherDetection
        )
    }

    fun mapFruitResponseToDomain(fruit: FruitResponse?): Fruit? {
        if (fruit == null) return null

        return Fruit(
            name = fruit.name,
            scientificName = fruit.scientificName,
            description = fruit.description,
            images = fruit.images,
        )
    }
}