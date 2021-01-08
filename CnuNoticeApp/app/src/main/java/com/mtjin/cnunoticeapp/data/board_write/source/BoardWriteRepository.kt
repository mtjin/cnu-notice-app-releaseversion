package com.mtjin.cnunoticeapp.data.board_write.source

import android.net.Uri
import com.mtjin.cnunoticeapp.data.board_list.Board
import io.reactivex.Completable
import io.reactivex.Single

interface BoardWriteRepository {
    fun insertBoard(board: Board, type: String): Completable //게시판 작성완료
    fun uploadImage(imageUriList: List<Uri>): Single<ArrayList<String>> //다중 이미지 업로드
}