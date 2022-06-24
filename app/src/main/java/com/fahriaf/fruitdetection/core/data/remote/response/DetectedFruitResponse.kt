package com.fahriaf.fruitdetection.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class DetectedFruitResponse(
    @SerializedName("fruit")
    val fruit: FruitResponse?,

    @SerializedName("similiarFruits")
    val otherDetection: List<FruitResponse>,
)