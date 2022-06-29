package com.fahriaf.fruitdetection.core.utils

import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.ParcelFileDescriptor
import java.io.ByteArrayInputStream
import java.io.FileDescriptor

fun Uri.toBitmap(contentResolver: ContentResolver): Bitmap? {
    try {
        val parcelFileDescriptor: ParcelFileDescriptor =
            contentResolver.openFileDescriptor(this, "r") as ParcelFileDescriptor
        val fileDescriptor: FileDescriptor = parcelFileDescriptor.fileDescriptor
        val image: Bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor)
        parcelFileDescriptor.close()
        return BitmapFactory.decodeStream(ByteArrayInputStream(image.toByteArray()))
    } catch (e: Exception) {
        e.printStackTrace()
        return null
    }
}