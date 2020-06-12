package com.mtjin.cnunoticeapp.data.bachelor

import androidx.room.Entity
import androidx.room.PrimaryKey

/*
*  num 값을 주요키 id 로 사용할려 했으나 공지 라는 이름의 아이디 떄문에 중복값이 발생하여 사용불가하다.
* */
@Entity(tableName = "bachelor")
data class BachelorNotice(
    val num: String,
    val title: String,
    @PrimaryKey
    var link: String
)