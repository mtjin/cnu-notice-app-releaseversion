package com.mtjin.cnunoticeapp.views.board_write

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mtjin.cnunoticeapp.base.BaseViewModel
import com.mtjin.cnunoticeapp.data.board_list.Board
import com.mtjin.cnunoticeapp.data.board_write.source.BoardWriteRepository
import com.mtjin.cnunoticeapp.utils.SingleLiveEvent
import com.mtjin.cnunoticeapp.utils.constants.TAG
import com.mtjin.cnunoticeapp.utils.constants.uuid
import com.mtjin.cnunoticeapp.utils.extensions.getTimestamp
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class BoardWriteViewModel(private val repository: BoardWriteRepository) :
    BaseViewModel() {
    lateinit var boardName: String
    var imageUriList = ArrayList<Uri>()

    private val _emptyMsg = SingleLiveEvent<String>()
    private val _writeComplete = SingleLiveEvent<Boolean>()
    private val _insertBoardSuccess = SingleLiveEvent<Boolean>()
    private val _pickImage = SingleLiveEvent<Unit>()

    val emptyMsg: LiveData<String> get() = _emptyMsg
    val writeComplete: LiveData<Boolean> get() = _writeComplete
    val insertBoardSuccess: LiveData<Boolean> get() = _insertBoardSuccess
    val pickImage: LiveData<Unit> get() = _pickImage

    var title = MutableLiveData("")
    var content = MutableLiveData("")


    //작성하기 버튼 클릭
    fun writeComplete() {
        _writeComplete.call()
    }

    //작성완료
    fun insertBoard() {
        when {
            content.value.isNullOrBlank() || title.value.isNullOrBlank() -> _emptyMsg.call()
            else -> { //작성조건 충족
                val board = Board(
                    id = getTimestamp(),
                    writerId = uuid,
                    title = title.value!!,
                    content = content.value!!
                )
                if (imageUriList.isNotEmpty()) { //사진 첨부 한 경우
                    repository.uploadImage(imageUriList)
                        .doOnError {
                            _insertBoardSuccess.value = false
                            Log.d(TAG, it.message.toString())
                        }.flatMapCompletable { imageResultList ->
                            board.imageList.addAll(imageResultList)
                            repository.insertBoard(board = board, type = boardName)
                        }
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe { showLottieProgress() }
                        .doAfterTerminate { hideLottieProgress() }
                        .subscribeBy(
                            onComplete = {
                                _insertBoardSuccess.value = true
                            },
                            onError = {
                                _insertBoardSuccess.value = false
                                Log.d(TAG, it.message.toString())
                            }
                        ).addTo(compositeDisposable)
                } else { //사진첨부 안한 경우
                    repository.insertBoard(board = board, type = boardName)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe { showLottieProgress() }
                        .doAfterTerminate { hideLottieProgress() }
                        .subscribeBy(
                            onComplete = {
                                _insertBoardSuccess.value = true
                            },
                            onError = {
                                _insertBoardSuccess.value = false
                                Log.d(TAG, it.message.toString())
                            }
                        ).addTo(compositeDisposable)
                }
            }
        }
    }

    //갤러리선택
    fun pickImage() {
        _pickImage.call()
    }


}