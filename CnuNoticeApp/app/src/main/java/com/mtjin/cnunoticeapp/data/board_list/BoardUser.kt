package com.mtjin.cnunoticeapp.data.board_list

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BoardUser(
    var id: String = "",
    var nickName: String = ""
) : Parcelable