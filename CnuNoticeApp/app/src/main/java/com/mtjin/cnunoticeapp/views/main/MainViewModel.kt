package com.mtjin.cnunoticeapp.views.main

import androidx.lifecycle.LiveData
import com.mtjin.cnunoticeapp.base.BaseViewModel
import com.mtjin.cnunoticeapp.utils.SingleLiveEvent

class MainViewModel : BaseViewModel() {
    private val _goNotice = SingleLiveEvent<Unit>()
    private val _goBoard = SingleLiveEvent<Unit>()
    private val _goAppOperation = SingleLiveEvent<Unit>()


    val goNotice: LiveData<Unit> get() = _goNotice
    val goBoard: LiveData<Unit> get() = _goBoard
    val goAppOperation: LiveData<Unit> get() = _goAppOperation

    fun goNotice() {
        _goNotice.call()
    }

    fun goBoard() {
        _goBoard.call()
    }

    fun goAppOperation() {
        _goAppOperation.call()
    }
}