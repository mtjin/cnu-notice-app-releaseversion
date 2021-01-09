package com.mtjin.cnunoticeapp.views.main

import androidx.lifecycle.LiveData
import com.mtjin.cnunoticeapp.base.BaseViewModel
import com.mtjin.cnunoticeapp.utils.SingleLiveEvent

class MainViewModel : BaseViewModel() {
    private val _goUnivNotice = SingleLiveEvent<Unit>()
    private val _goBoard = SingleLiveEvent<Unit>()
    private val _goAppNotice = SingleLiveEvent<Unit>()


    val goUnivNotice: LiveData<Unit> get() = _goUnivNotice
    val goBoard: LiveData<Unit> get() = _goBoard
    val goAppNotice: LiveData<Unit> get() = _goAppNotice

    fun goUnivNotice() {
        _goUnivNotice.call()
    }

    fun goBoard() {
        _goBoard.call()
    }

    fun goAppNotice() {
        _goAppNotice.call()
    }
}