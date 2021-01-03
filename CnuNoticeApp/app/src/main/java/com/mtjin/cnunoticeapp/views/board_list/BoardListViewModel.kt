package com.mtjin.cnunoticeapp.views.board_list

import com.mtjin.cnunoticeapp.base.BaseViewModel
import com.mtjin.cnunoticeapp.utils.SingleLiveEvent

class BoardListViewModel : BaseViewModel() {

    private val _goBoardWrite = SingleLiveEvent<Unit>()
    private val _boardName = SingleLiveEvent<String>()

    val goBoardWrite get() = _goBoardWrite
    val boardName get() = _boardName

    fun goBoardWrite() {
        _goBoardWrite.call()
    }

    fun setBoardName(name: String) {
        _boardName.value = name
    }
}