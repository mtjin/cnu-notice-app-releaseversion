package com.mtjin.cnunoticeapp.utils.extensions

import android.text.format.DateFormat
import java.util.*

//현재 타임스템프 얻기
fun getTimestamp() = Calendar.getInstance().timeInMillis

fun Long.convertBoardTime(): String {
    val now = Calendar.getInstance()
    val diffInMillis = now.timeInMillis - this
    val diffHour = diffInMillis / 1000 / 60 / 60L
    return if (diffHour < 1) { //분 단위 전
        (diffInMillis / 1000 / 60L).toString() + "분 전"
    } else {
        if (diffHour < 24) { //시간 단위 전 (08:23)
            this.convertHourMinute()
        } else { // 일 수 전 (2021.01.07 08:23)
            this.convertDateTimeMinute()
        }
    }
}

//타임스탬프 -> 시간:분 (08:23)
fun Long.convertHourMinute(): String = DateFormat.format("HH:mm", this).toString()

//타임스탬프 -> 날짜 시간:분 (2021.01.07 08:23)
fun Long.convertDateTimeMinute(): String =
    DateFormat.format("yyyy.MM.dd HH:mm", this).toString()

