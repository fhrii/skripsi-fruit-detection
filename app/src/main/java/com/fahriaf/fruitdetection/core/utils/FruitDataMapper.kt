package com.fahriaf.fruitdetection.core.utils

import com.fahriaf.fruitdetection.core.data.remote.response.DetectedFruitResponse
import com.fahriaf.fruitdetection.core.data.remote.response.FruitResponse
import com.fahriaf.fruitdetection.core.domain.model.DetectedFruit
import com.fahriaf.fruitdetection.core.domain.model.Fruit

object FruitDataMapper {
    fun mapDetectedFruitResponseToDomain(detectedFruit: DetectedFruitResponse): DetectedFruit {
        return DetectedFruit(
            fruit = if (detectedFruit.fruit != null) mapFruitResponseToDomain(detectedFruit.fruit) else null,
            otherDetection = mapListFruitResponseToListDomain(detectedFruit.otherDetection)
        )
    }

    fun mapFruitResponseToDomain(fruit: FruitResponse): Fruit {
        return Fruit(
            name = fruit.name,
            scientificName = fruit.scientificName,
            description = fruit.description,
            images = FruitImageDataMapper.mapListFruitImageResponseToListDomain(fruit.images),
        )
    }

    fun mapListFruitResponseToListDomain(fruits: List<FruitResponse>): List<Fruit> {
        return fruits.map { mapFruitResponseToDomain(it) }
    }
}