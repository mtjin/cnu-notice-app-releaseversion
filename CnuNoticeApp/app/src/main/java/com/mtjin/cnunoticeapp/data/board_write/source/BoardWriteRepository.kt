package com.mtjin.cnunoticeapp.data.board_write.source

import com.mtjin.cnunoticeapp.data.board_list.Board
import io.reactivex.Completable

interface BoardWriteRepository {
    fun insertBoard(board: Board): Completable //게시판 작성완료
}