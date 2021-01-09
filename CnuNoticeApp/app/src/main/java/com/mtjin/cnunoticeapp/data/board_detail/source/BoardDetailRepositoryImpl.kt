package com.mtjin.cnunoticeapp.data.board_detail.source

import android.util.Log
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.mtjin.cnunoticeapp.data.board_detail.Comment
import com.mtjin.cnunoticeapp.data.board_list.Board
import com.mtjin.cnunoticeapp.utils.constants.COLUMN_COMMENT_COUNT
import com.mtjin.cnunoticeapp.utils.constants.DB_BOARD
import com.mtjin.cnunoticeapp.utils.constants.DB_COMMENT
import com.mtjin.cnunoticeapp.utils.constants.TAG
import io.reactivex.BackpressureStrategy
import io.reactivex.Completable
import io.reactivex.Flowable

class BoardDetailRepositoryImpl(private val db: FirebaseFirestore) : BoardDetailRepository {
    //댓글 작성
    override fun insertComment(type: String, board: Board, comment: Comment): Completable {
        return Completable.create { emitter ->
            val boardName = DB_BOARD + "_" + type
            db.collection(boardName).document(board.id.toString())
                .collection(DB_COMMENT).document(comment.id.toString())
                .set(comment)
                .addOnSuccessListener {
                    db.collection(boardName).document(board.id.toString())
                        .update(COLUMN_COMMENT_COUNT, FieldValue.increment(1))
                    emitter.onComplete()
                }
                .addOnFailureListener { emitter.onError(it) }
        }
    }

    override fun updateComment(comment: Comment): Completable {
        TODO("Not yet implemented")
    }

    override fun updateBoard(board: Board): Completable {
        TODO("Not yet implemented")
    }

    //댓글 불러오기
    override fun requestComments(type: String, board: Board): Flowable<List<Comment>> {
        return Flowable.create<List<Comment>>({ emitter ->
            val boardName = DB_BOARD + "_" + type
            db.collection(boardName).document(board.id.toString())
                .collection(DB_COMMENT).addSnapshotListener { value, e ->
                    if (e != null) {
                        Log.w(TAG, "Listen failed.", e)
                        emitter.onError(e)
                    }
                    val comments = ArrayList<Comment>()
                    for (comment in value!!) {
                        comments.add(comment.toObject(Comment::class.java))
                    }
                    emitter.onNext(comments)
                }
        }, BackpressureStrategy.BUFFER)
    }

}
