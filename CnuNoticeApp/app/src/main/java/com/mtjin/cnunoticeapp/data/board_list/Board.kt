package com.mtjin.cnunoticeapp.data.board_list

data class Board(
    var id: Long = 0,
    var writerId: String = "",
    var title: String = "",
    var content: String = "",
    var image : String = "",
    var commentList: ArrayList<BoardUser> = ArrayList(),
    var recommendList: ArrayList<BoardUser> = ArrayList()
)