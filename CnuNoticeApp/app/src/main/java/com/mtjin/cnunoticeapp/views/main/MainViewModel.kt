package com.mtjin.cnunoticeapp.views.main

import androidx.lifecycle.LiveData
import com.mtjin.cnunoticeapp.base.BaseViewModel
import com.mtjin.cnunoticeapp.utils.SingleLiveEvent

class MainViewModel : BaseViewModel() {
    private val _goNotice = SingleLiveEvent<Unit>()
    private val _goBoard = SingleLiveEvent<Unit>()

    val goNotice: LiveData<Unit> get() = _goNotice
    val goBoard: LiveData<Unit> get() = _goBoard

    fun goNotice() {
        _goNotice.call()
    }

    fun goBoard() {
        _goBoard.call()
    }
}