package com.mtjin.cnunoticeapp.views.board_write

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mtjin.cnunoticeapp.base.BaseViewModel
import com.mtjin.cnunoticeapp.data.board_list.Board
import com.mtjin.cnunoticeapp.data.board_write.source.BoardWriteRepository
import com.mtjin.cnunoticeapp.utils.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class BoardWriteViewModel(private val repository: BoardWriteRepository) :
    BaseViewModel() {
    lateinit var boardName: String

    private val _emptyMsg = SingleLiveEvent<String>()
    private val _insertBoardSuccess = SingleLiveEvent<Boolean>()

    val emptyMsg: LiveData<String> get() = _emptyMsg
    val insertBoardSuccess: LiveData<Boolean> get() = _insertBoardSuccess

    var title = MutableLiveData("")
    var content = MutableLiveData("")


    //작성완료
    fun insertBoard() {
        when {
            content.value.isNullOrBlank() || title.value.isNullOrBlank() -> _emptyMsg.call()
            else -> { //작성조건 충족
                repository.insertBoard(board = Board(), type = boardName)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe { showProgress() }
                    .doAfterTerminate { hideProgress() }
                    .subscribeBy(
                        onComplete = {},
                        onError = {}
                    )
            }
        }
    }


}