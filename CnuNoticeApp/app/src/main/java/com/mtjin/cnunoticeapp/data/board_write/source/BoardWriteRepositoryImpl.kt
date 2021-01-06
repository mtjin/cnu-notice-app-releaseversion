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

    override fun uploadImage(imageUri: Uri): Single<String> {
        val storageRef = storage.child(getTimestamp().toString() + ".png")
        val uploadTask = storageRef.putFile(imageUri)
        return Single.create { emitter ->
            uploadTask.continueWithTask { task ->
                if (!task.isSuccessful) {
                    task.exception?.let { throw it }
                }
                storageRef.downloadUrl
            }.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val downloadUri = task.result.toString()
                    emitter.onSuccess(downloadUri)
                } else {
                    emitter.onError(Throwable(ERR_UPLOAD_IMAGE))
                }
            }
        }
    }
}