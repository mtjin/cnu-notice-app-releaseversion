package com.mtjin.cnunoticeapp.views.board_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mtjin.cnunoticeapp.base.BaseViewModel
import com.mtjin.cnunoticeapp.data.board_detail.Comment
import com.mtjin.cnunoticeapp.data.board_detail.source.BoardDetailRepository
import com.mtjin.cnunoticeapp.utils.SingleLiveEvent
import com.mtjin.cnunoticeapp.utils.constants.uuid
import com.mtjin.cnunoticeapp.utils.extensions.getTimestamp
import kotlin.random.Random
import kotlin.random.nextInt

class BoardDetailViewModel(private val repository: BoardDetailRepository) : BaseViewModel() {
    lateinit var boardName: String //게시판 이름
    lateinit var firstNameList: Array<String> //닉네임 형용사(1)
    lateinit var secondNameList: Array<String> //닉네임 이름(2)
    lateinit var nickName: String //댓글 닉네임
    var commentInput = MutableLiveData("") //댓글 EditText

    private val _commentList = SingleLiveEvent<List<Comment>>()

    val commentList: LiveData<List<Comment>> get() = _commentList

    fun insertComment() {
        if (!commentInput.value.isNullOrBlank()) {
            repository.insertComment(
                Comment(
                    id = getTimestamp(),
                    userId = uuid,
                    nickName = "",
                    content = commentInput.value.toString()
                )
            )
        }
    }

    fun initRandomNickName() {
        val first = Random(getTimestamp()).nextInt(firstNameList.indices)
        val second = Random(getTimestamp()).nextInt(secondNameList.indices)
    }
}