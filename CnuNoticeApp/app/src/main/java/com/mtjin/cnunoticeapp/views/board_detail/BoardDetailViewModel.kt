package com.mtjin.cnunoticeapp.views.board_detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mtjin.cnunoticeapp.base.BaseViewModel
import com.mtjin.cnunoticeapp.data.board_detail.Comment
import com.mtjin.cnunoticeapp.data.board_detail.source.BoardDetailRepository
import com.mtjin.cnunoticeapp.data.board_list.Board
import com.mtjin.cnunoticeapp.utils.SingleLiveEvent
import com.mtjin.cnunoticeapp.utils.constants.TAG
import com.mtjin.cnunoticeapp.utils.constants.uuid
import com.mtjin.cnunoticeapp.utils.extensions.getTimestamp
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlin.random.Random
import kotlin.random.nextInt

class BoardDetailViewModel(private val repository: BoardDetailRepository) : BaseViewModel() {
    lateinit var boardName: String //게시판 이름
    lateinit var board: Board //게시판
    lateinit var firstNameList: Array<String> //닉네임 형용사(1)
    lateinit var secondNameList: Array<String> //닉네임 이름(2)
    lateinit var nickName: String //댓글 닉네임
    var commentInput = MutableLiveData("") //댓글 EditText

    private val _commentList = SingleLiveEvent<List<Comment>>()

    val commentList: LiveData<List<Comment>> get() = _commentList

    fun insertComment() {
        initRandomNickName()
        if (!commentInput.value.isNullOrBlank()) {
            repository.insertComment(
                type = boardName,
                board = board,
                comment = Comment(
                    id = getTimestamp(),
                    userId = uuid,
                    nickName = nickName,
                    content = commentInput.value.toString()
                )
            ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onComplete = {
                        commentInput.value = ""
                    },
                    onError = {

                    }
                ).addTo(compositeDisposable)
        }
    }

    fun requestComments() {
        repository.requestComments(type = boardName, board = board)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {
                    _commentList.value = it
                    Log.d(TAG, "requestComments() onNext -> $it")
                },
                onError = {
                    Log.d(TAG, "requestComments() OnError-> $it")
                },
                onComplete = {
                    Log.d(TAG, "requestComments() onComplete")
                }
            ).addTo(compositeDisposable)
    }

    private fun initRandomNickName() {
        if (!this::nickName.isInitialized) {
            for (comment in commentList.value!!) { //자기 닉네임 있는 경우
                if (comment.userId == uuid) {
                    nickName = comment.nickName
                    return
                }
            }
            if (board.writerId == uuid) {
                nickName = "글쓴이"
            } else {
                val first: String
                val second: String
                while (true) {
                    first = firstNameList[Random(getTimestamp()).nextInt(firstNameList.indices)]
                    second = secondNameList[Random(getTimestamp()).nextInt(secondNameList.indices)]
                    for (comment in commentList.value!!) {
                        if (comment.nickName == "$first $second") continue
                    }
                    nickName = "$first $second"
                    break
                }
            }
        }
    }
}