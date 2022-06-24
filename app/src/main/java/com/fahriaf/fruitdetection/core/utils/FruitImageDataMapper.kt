package com.fahriaf.fruitdetection.core.utils

import com.fahriaf.fruitdetection.core.data.remote.response.FruitImageResponse
import com.fahriaf.fruitdetection.core.domain.model.FruitImage

object FruitImageDataMapper {
    fun mapFruitImageResponseToDomain(fruitImage: FruitImageResponse): FruitImage {
        return FruitImage(
            url = fruitImage.url
        )
    }

    fun mapListFruitImageResponseToListDomain(fruitImages: List<FruitImageResponse>): List<FruitImage> {
        return fruitImages.map { mapFruitImageResponseToDomain(it) }
    }
}