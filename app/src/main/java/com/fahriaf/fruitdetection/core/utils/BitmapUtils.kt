package com.fahriaf.fruitdetection.core.utils

import android.graphics.Bitmap
import java.io.ByteArrayOutputStream

fun Bitmap.toByteArray(): ByteArray {
    val out = ByteArrayOutputStream()
    this.compress(Bitmap.CompressFormat.JPEG, 80, out)
    return out.toByteArray()
}