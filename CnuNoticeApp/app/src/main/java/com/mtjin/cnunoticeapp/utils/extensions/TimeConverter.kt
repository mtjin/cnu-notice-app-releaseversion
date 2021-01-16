package com.mtjin.cnunoticeapp.utils.extensions

import android.text.format.DateFormat
import android.util.Log
import java.util.*
import java.util.concurrent.TimeUnit

//현재 타임스템프 얻기
fun getTimestamp() = Calendar.getInstance(Locale.KOREA).timeInMillis

fun Long.convertBoardTime(): String {
    val now = Calendar.getInstance(Locale.KOREA)
    val diffInMillis = now.timeInMillis - this //현재와의 밀리초 차이
    val todayOfDay = TimeUnit.MILLISECONDS.toDays(now.timeInMillis)
    val thisOfDay = TimeUnit.MILLISECONDS.toDays(this)
    val currentDiffHour = diffInMillis / 1000 / 60 / 60L //현재와 시간 차이
    return if (currentDiffHour < 1) { //현재시간 60분 전
        (diffInMillis / 1000 / 60L).toString() + "분 전"
    } else {
        if (todayOfDay == thisOfDay) { //오늘
            this.convertHourMinute() //(08:23)
        } else { // 하루 이상 지난 날
            this.convertDateTimeMinute() // (2021.01.07 08:23)
        }
    }
}

//타임스탬프 -> 시간:분 (08:23)
fun Long.convertHourMinute(): String = DateFormat.format("HH:mm", this).toString()

//타임스탬프 -> 날짜 시간:분 (2021.01.07 08:23)
fun Long.convertDateTimeMinute(): String =
    DateFormat.format("yyyy.MM.dd HH:mm", this).toString()

