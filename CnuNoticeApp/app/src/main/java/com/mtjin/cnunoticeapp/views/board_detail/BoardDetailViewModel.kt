package com.mtjin.cnunoticeapp.views.board_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mtjin.cnunoticeapp.base.BaseViewModel
import com.mtjin.cnunoticeapp.data.board_detail.Comment
import com.mtjin.cnunoticeapp.data.board_detail.source.BoardDetailRepository
import com.mtjin.cnunoticeapp.utils.SingleLiveEvent
import com.mtjin.cnunoticeapp.utils.extensions.getTimestamp

class BoardDetailViewModel(private val repository: BoardDetailRepository) : BaseViewModel() {
    lateinit var boardName: String //게시판 이름
    var commentInput = MutableLiveData("") //댓글 EditText

    private val _commentList = SingleLiveEvent<List<Comment>>()

    val commentList: LiveData<List<Comment>> get() = _commentList

    /*var userId: String = "",
        var nickName: String = "",
        var content: String = "",
        var recommend: Int = 0*/
    fun insertComment() {
        if (!commentInput.value.isNullOrBlank()) {
            //repository.insertComment(Comment(id = getTimestamp(), userId = FirebaseHelper))
        }
    }
}