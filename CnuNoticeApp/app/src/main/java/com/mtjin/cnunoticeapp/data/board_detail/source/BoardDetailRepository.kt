package com.mtjin.cnunoticeapp.data.board_detail.source

import com.mtjin.cnunoticeapp.data.board_detail.Comment
import com.mtjin.cnunoticeapp.data.board_list.Board
import io.reactivex.Completable
import io.reactivex.Flowable

interface BoardDetailRepository {
    fun insertComment(type: String, board: Board, comment: Comment): Completable //댓글 작성
    fun updateComment(type: String, boardId: Long, commentId: Long): Completable //댓글 업데이트(추천)
    fun updateBoard(type: String, boardId: Long): Completable //게시판 업데이트(추천)
    fun requestComments(type: String, board: Board): Flowable<List<Comment>> //댓글 불러오기
}