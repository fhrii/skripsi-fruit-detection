package com.fahriaf.fruitdetection.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class FruitImage(
    val url: String,
) : Parcelable