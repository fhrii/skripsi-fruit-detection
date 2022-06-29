package com.fahriaf.fruitdetection.core.utils

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.File

fun File.createImageTempFile(
    context: Context,
    name: String = "tmp_image_file",
    extension: String = "jpg"
): Uri {
    val tempFile = File.createTempFile(name, ".$extension", this).apply {
        createNewFile()
        deleteOnExit()
    }
    return FileProvider.getUriForFile(
        context,
        "com.fahriaf.fruitdetection.provider",
        tempFile
    )
}