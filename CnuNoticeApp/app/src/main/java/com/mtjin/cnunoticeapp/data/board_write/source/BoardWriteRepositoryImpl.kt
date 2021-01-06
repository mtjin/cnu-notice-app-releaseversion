package com.mtjin.cnunoticeapp.data.board_write.source

import com.google.firebase.firestore.FirebaseFirestore
import com.mtjin.cnunoticeapp.data.board_list.Board
import io.reactivex.Completable

class BoardWriteRepositoryImpl(private val db: FirebaseFirestore) : BoardWriteRepository {
    //게시판 작성완료
    override fun insertBoard(board: Board): Completable {
        TODO("Not yet implemented")
    }
}