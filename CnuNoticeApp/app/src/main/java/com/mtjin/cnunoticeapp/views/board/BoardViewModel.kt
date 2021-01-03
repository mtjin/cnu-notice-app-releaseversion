package com.mtjin.cnunoticeapp.views.board

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mtjin.cnunoticeapp.base.BaseViewModel
import com.mtjin.cnunoticeapp.data.board.source.BoardRepository
import com.mtjin.cnunoticeapp.utils.SingleLiveEvent

class BoardViewModel(private val repository: BoardRepository) : BaseViewModel() {
    private val _univAuthSuccess = SingleLiveEvent<Boolean>() //대학교인증 EditText
    private val _alreadyUnivAuth = SingleLiveEvent<Boolean>() //이미 대학교 인증 여부
    private val _goBoardList = SingleLiveEvent<String>() //해당 주제 대학교 게시판 이동

    val univAuthSuccess: LiveData<Boolean> get() = _univAuthSuccess
    val alreadyUnivAuth: LiveData<Boolean> get() = _alreadyUnivAuth
    val goBoardList: LiveData<String> get() = _goBoardList

    // two-way binding
    var univAuthText: MutableLiveData<String> = MutableLiveData("")

    fun requestUnivAuth() {
        if (univAuthText.value.toString() == UNIV_AUTH_ANSWER) {
            _univAuthSuccess.value = true
            repository.univAuth = true
        } else {
            _univAuthSuccess.value = false
        }
    }

    fun requestAlreadyAuth() {
        _alreadyUnivAuth.value = repository.univAuth
    }

    fun goBoardList(board: String) {
        _goBoardList.value = board
    }

    companion object {
        const val UNIV_AUTH_ANSWER = "통합정보시스템"
    }
}