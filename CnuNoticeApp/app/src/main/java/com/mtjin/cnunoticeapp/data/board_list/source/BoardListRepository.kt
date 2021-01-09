package com.mtjin.cnunoticeapp.data.board_list.source

import com.mtjin.cnunoticeapp.data.board_list.Board
import io.reactivex.Single

interface BoardListRepository {
    fun requestBoards(page: Int, type: String): Single<List<Board>> //게시물 불러오기
    fun searchBoard(page: Int, type: String, searchName: String): Single<List<Board>>//게시물 검색
}