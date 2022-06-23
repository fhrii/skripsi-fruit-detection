package com.fahriaf.fruitdetection.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetectedFruit(
    val fruit: Fruit?,
    val otherDetection: List<String>
) : Parcelable
