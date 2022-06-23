package com.fahriaf.fruitdetection.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class FruitResponse(
    @SerializedName("name")
    val name: String,

    @SerializedName("scientific_name")
    val scientificName: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("images")
    val images: List<String>
)
