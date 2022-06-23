package com.fahriaf.fruitdetection.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class DetectedFruitResponse(
    @SerializedName("fruit")
    val fruit: FruitResponse?,

    @SerializedName("other_detection")
    val otherDetection: List<String>,
)