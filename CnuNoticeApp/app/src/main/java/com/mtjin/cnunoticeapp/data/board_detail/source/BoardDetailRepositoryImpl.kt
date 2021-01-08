package com.mtjin.cnunoticeapp.data.board_detail.source

import com.google.firebase.firestore.FirebaseFirestore
import com.mtjin.cnunoticeapp.data.board_detail.Comment
import com.mtjin.cnunoticeapp.data.board_list.Board
import io.reactivex.Completable
import io.reactivex.Single

class BoardDetailRepositoryImpl( private val db: FirebaseFirestore) : BoardDetailRepository {
    override fun insertComment(comment: Comment): Completable {
        TODO("Not yet implemented")
    }

    override fun updateComment(comment: Comment): Completable {
        TODO("Not yet implemented")
    }

    override fun updateBoard(board: Board): Completable {
        TODO("Not yet implemented")
    }

    override fun requestComments(type: String, board: Board): Single<List<Comment>> {
        return Single.create<List<Comment>> {

        }
    }
}