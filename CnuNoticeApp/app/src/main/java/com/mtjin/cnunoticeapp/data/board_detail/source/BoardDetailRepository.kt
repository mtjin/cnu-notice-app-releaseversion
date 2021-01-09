package com.mtjin.cnunoticeapp.data.board_detail.source

import com.mtjin.cnunoticeapp.data.board_detail.Comment
import com.mtjin.cnunoticeapp.data.board_list.Board
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface BoardDetailRepository {
    fun insertComment(type: String, board: Board,comment: Comment): Completable //댓글 작성
    fun updateComment(comment: Comment): Completable //댓글 업데이트(추천)
    fun updateBoard(board: Board): Completable //게시판 업데이트(추천)
    fun requestComments(type: String, board: Board): Flowable<List<Comment>> //댓글 불러오기
}