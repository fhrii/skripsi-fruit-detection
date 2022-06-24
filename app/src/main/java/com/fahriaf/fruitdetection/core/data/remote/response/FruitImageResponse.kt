package com.fahriaf.fruitdetection.core.data.remote.response

import com.google.gson.annotations.SerializedName

class FruitImageResponse(
    @SerializedName("url")
    val url: String,
)