package com.mtjin.cnunoticeapp.data.board_list

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Board(
    var id: Long = 0,
    var writerId: String = "",
    var title: String = "",
    var content: String = "",
    var imageList: ArrayList<String> = ArrayList(),
    var commentCount: Int = 0,
    var recommendList: ArrayList<String> = ArrayList()
) : Parcelable