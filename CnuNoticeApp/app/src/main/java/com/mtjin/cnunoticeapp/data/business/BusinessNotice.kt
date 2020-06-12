package com.mtjin.cnunoticeapp.data.business

import androidx.room.Entity
import androidx.room.PrimaryKey

/*
*  num 값을 주요키 id 로 사용할려 했으나 공지 라는 이름의 아이디 떄문에 중복값이 발생하여 사용불가하다.
* */
@Entity(tableName = "business")
data class BusinessNotice(
    val num: String,
    val title: String,
    @PrimaryKey
    var link: String
)