package com.fahriaf.fruitdetection.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Fruit(
    val name: String,
    val scientificName: String,
    val description: String,
    val images: List<String>
) : Parcelable
