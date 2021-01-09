package com.mtjin.cnunoticeapp.data.board_list.source

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.mtjin.cnunoticeapp.data.board_list.Board
import com.mtjin.cnunoticeapp.utils.constants.COLUMN_ID
import com.mtjin.cnunoticeapp.utils.constants.DB_BOARD
import io.reactivex.Single

class BoardListRepositoryImpl(private val db: FirebaseFirestore) : BoardListRepository {
    override fun requestBoards(page: Int, type: String): Single<List<Board>> {
        return Single.create<List<Board>> { emitter ->
            db.collection(DB_BOARD + "_" + type)
                .orderBy(COLUMN_ID, Query.Direction.DESCENDING)
                .limit(page.toLong())
                .get()
                .addOnSuccessListener { documents ->
                    val boards = ArrayList<Board>()
                    for (document in documents) {
                        boards.add(document.toObject(Board::class.java))
                    }
                    emitter.onSuccess(boards)
                }.addOnFailureListener {
                    emitter.onError(it)
                }
        }
    }
}