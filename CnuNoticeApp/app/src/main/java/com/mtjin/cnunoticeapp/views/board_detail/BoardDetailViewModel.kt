package com.mtjin.cnunoticeapp.views.board_detail

import androidx.lifecycle.LiveData
import com.mtjin.cnunoticeapp.base.BaseViewModel
import com.mtjin.cnunoticeapp.data.board_detail.Comment
import com.mtjin.cnunoticeapp.data.board_detail.source.BoardDetailRepository
import com.mtjin.cnunoticeapp.utils.SingleLiveEvent

class BoardDetailViewModel(private val repository: BoardDetailRepository) : BaseViewModel() {
    lateinit var boardName : String

    private val _commentInput = SingleLiveEvent<Boolean>()
    private val _commentList = SingleLiveEvent<List<Comment>>()

    val commentInput: LiveData<Boolean> get() = commentInput
    val commentList: LiveData<List<Comment>> get() = _commentList

    /*var userId: String = "",
        var nickName: String = "",
        var content: String = "",
        var recommend: Int = 0*/
    fun insertComment() {

    }
}