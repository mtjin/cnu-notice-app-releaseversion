package com.mtjin.cnunoticeapp.data.board_write.source

import android.net.Uri
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.StorageReference
import com.mtjin.cnunoticeapp.data.board_list.Board
import com.mtjin.cnunoticeapp.utils.constants.DB_BOARD
import com.mtjin.cnunoticeapp.utils.constants.ERR_FIRESTORE
import com.mtjin.cnunoticeapp.utils.constants.ERR_UPLOAD_IMAGE
import com.mtjin.cnunoticeapp.utils.extensions.getTimestamp
import io.reactivex.Completable
import io.reactivex.Single

class BoardWriteRepositoryImpl(
    private val db: FirebaseFirestore,
    private val storage: StorageReference
) : BoardWriteRepository {
    //게시판 작성완료
    override fun insertBoard(board: Board, type: String): Completable {
        return Completable.create { emitter ->
            db.collection(DB_BOARD + "_" + type)
                .document(board.id.toString())
                .set(board)
                .addOnSuccessListener {
                    emitter.onComplete()
                }.addOnFailureListener {
                    emitter.onError(Throwable(ERR_FIRESTORE))
                }
        }
    }

    //다중 이미지 업로드
    override fun uploadImage(imageUriList: List<Uri>): Single<ArrayList<String>> {
        return Single.create { emitter ->
            val resultList = ArrayList<String>()
            for (imageUri in imageUriList) {
                val storageRef = storage.child("게시판/" + getTimestamp().toString() + ".png")
                val uploadTask = storageRef.putFile(imageUri)
                uploadTask.continueWithTask { task ->
                    storageRef.downloadUrl
                }.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val downloadUri = task.result.toString()
                        resultList.add(downloadUri)
                        if (resultList.size == imageUriList.size) emitter.onSuccess(resultList) //모든 이미지 업로드 완료
                    } else {
                        emitter.onError(Throwable(ERR_UPLOAD_IMAGE))
                    }
                }
            }
        }
    }
}