package com.arifwidayana.notebookstore.common.utils

import android.annotation.SuppressLint
import android.os.Build
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Date

@SuppressLint("SimpleDateFormat")
fun pickDateTimeNow(): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"))
        val time = LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss"))
        date+time
    } else {
        SimpleDateFormat("dd-MMM-yyyy hh:mm:ss").format(Date(System.currentTimeMillis()))
    }
}