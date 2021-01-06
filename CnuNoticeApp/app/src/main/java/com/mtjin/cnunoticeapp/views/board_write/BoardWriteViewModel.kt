package com.mtjin.cnunoticeapp.views.board_write

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mtjin.cnunoticeapp.base.BaseViewModel
import com.mtjin.cnunoticeapp.utils.SingleLiveEvent

class BoardWriteViewModel : BaseViewModel() {
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

            }
        }
    }


}