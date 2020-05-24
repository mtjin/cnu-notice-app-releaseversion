package com.mtjin.cnunoticeapp.data.favorite

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

/*
*  num 값을 주요키 id 로 사용할려 했으나 공지 라는 이름의 아이디 떄문에 중복값이 발생하여 사용불가하다.
* */
@Entity(tableName = "favorite")
data class FavoriteNotice(
    @PrimaryKey
    val num: String,
    val title: String,
    val link: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(num)
        parcel.writeString(title)
        parcel.writeString(link)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FavoriteNotice> {
        override fun createFromParcel(parcel: Parcel): FavoriteNotice {
            return FavoriteNotice(parcel)
        }

        override fun newArray(size: Int): Array<FavoriteNotice?> {
            return arrayOfNulls(size)
        }
    }
}