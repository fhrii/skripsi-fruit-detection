package com.fahriaf.fruitdetection.core.utils

import java.text.SimpleDateFormat
import java.util.*

object DateHelper {
    private var fileNameProfileFormat = SimpleDateFormat("ddMMyyyy_HHmmss", Locale.getDefault())
    fun dateNowString() = fileNameProfileFormat.format(Date())
}