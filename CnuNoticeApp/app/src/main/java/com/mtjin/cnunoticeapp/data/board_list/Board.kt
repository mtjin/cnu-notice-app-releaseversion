package com.mtjin.cnunoticeapp.data.board_list

import android.os.Parcelable
import com.mtjin.cnunoticeapp.data.board_detail.Comment
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Board(
    var id: Long = 0,
    var writerId: String = "",
    var title: String = "",
    var content: String = "",
    var imageList: ArrayList<String> = ArrayList(),
    var commentList: ArrayList<Comment> = ArrayList(),
    var recommendList: ArrayList<BoardUser> = ArrayList()
) : Parcelable