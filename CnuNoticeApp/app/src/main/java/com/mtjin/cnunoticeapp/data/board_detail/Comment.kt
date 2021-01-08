package com.mtjin.cnunoticeapp.data.board_detail

data class Comment(
    var id: String = "",
    var userId: String = "",
    var nickName: String = "",
    var content: String = "",
    var recommend: Int = 0
)