package com.mtjin.cnunoticeapp.views.board_list

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.mtjin.cnunoticeapp.base.BaseViewModel
import com.mtjin.cnunoticeapp.data.board_list.Board
import com.mtjin.cnunoticeapp.data.board_list.source.BoardListRepository
import com.mtjin.cnunoticeapp.utils.SingleLiveEvent
import com.mtjin.cnunoticeapp.utils.constants.TAG
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class BoardListViewModel(private val repository: BoardListRepository) : BaseViewModel() {

    private val _goBoardWrite = SingleLiveEvent<Unit>()
    private val _boardName = SingleLiveEvent<String>()
    private val _boardList = SingleLiveEvent<ArrayList<Board>>()
    private val _clickSearch = MutableLiveData<Boolean>(false)

    val goBoardWrite get() = _goBoardWrite
    val boardName get() = _boardName
    val boardList get() = _boardList
    val clickSearch get() = _clickSearch

    fun requestBoards(page: Int) {
        repository.requestBoards(page = page, type = boardName.value!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { showProgress() }
            .doAfterTerminate { hideProgress() }
            .subscribeBy(
                onSuccess = { boards ->
                    Log.d(TAG, "BoardListViewModel requestBoards() -> $boards")
                    _boardList.value = boards as ArrayList<Board>?
                },
                onError = {
                    Log.d(TAG, "BoardListViewModel requestBoards() -> $it")
                }
            ).addTo(compositeDisposable)
    }

    fun clickSearch() {
        _clickSearch.value = !clickSearch.value!!
    }

    fun searchBoard(name: String, page: Int) {
        repository.searchBoard(page = page, searchName = name, type = boardName.value!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { boards ->
                    Log.d(TAG, "BoardListViewModel searchBoard() -> $boards")
                    _boardList.value = boards as ArrayList<Board>?
                },
                onError = {
                    Log.d(TAG, "BoardListViewModel searchBoard() -> $it")
                }
            ).addTo(compositeDisposable)
    }


    fun goBoardWrite() {
        _goBoardWrite.call()
    }

    fun setBoardName(name: String) {
        _boardName.value = name
    }

    fun clearBoards() {
        _boardList.value?.clear()
    }
}