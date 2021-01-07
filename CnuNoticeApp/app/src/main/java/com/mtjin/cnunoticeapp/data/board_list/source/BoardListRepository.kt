package com.mtjin.cnunoticeapp.data.board_list.source

import com.mtjin.cnunoticeapp.data.board_list.Board
import io.reactivex.Single

interface BoardListRepository {
    fun requestBoards(page: Int, type: String): Single<List<Board>>
}