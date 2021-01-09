package com.mtjin.cnunoticeapp.data.board_detail.source

import com.mtjin.cnunoticeapp.data.board_detail.Comment
import com.mtjin.cnunoticeapp.data.board_list.Board
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface BoardDetailRepository {
    fun insertComment(comment: Comment): Completable
    fun updateComment(comment: Comment): Completable
    fun updateBoard(board: Board): Completable
    fun requestComments(type: String, board: Board): Flowable<List<Comment>>
}