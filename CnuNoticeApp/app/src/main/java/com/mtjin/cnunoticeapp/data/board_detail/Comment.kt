package com.mtjin.cnunoticeapp.data.board_detail

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Comment(
    var id: Long = 0,
    var userId: String = "",
    var nickName: String = "",
    var content: String = "",
    var recommendList: List<String> = ArrayList()
) : Parcelable