package com.mtjin.cnunoticeapp.views.favorite

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mtjin.cnunoticeapp.base.BaseViewModel
import com.mtjin.cnunoticeapp.data.favorite.FavoriteNotice
import com.mtjin.cnunoticeapp.data.favorite.source.FavoriteNoticeRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FavoriteViewModel(private val repository: FavoriteNoticeRepository) :
    BaseViewModel() {
    private val _noticeList = MutableLiveData<ArrayList<FavoriteNotice>>()

    val noticeList: LiveData<ArrayList<FavoriteNotice>> get() = _noticeList

    fun requestNotice() {
        compositeDisposable.add(
            repository.requestNotice()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { showProgress() }
                .doAfterTerminate { hideProgress() }
                .subscribe({
                    _noticeList.value = it as ArrayList<FavoriteNotice>?
                }, {
                    Log.d(TAG, "" + it)
                })
        )
    }

    fun insert(notice: FavoriteNotice) {
        compositeDisposable.add(
            repository.insertNotice(notice)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { showProgress() }
                .doAfterTerminate { hideProgress() }
                .subscribe({
                }, {
                    Log.d(TAG, "" + it)
                })
        )
    }

    fun delete(notice: FavoriteNotice) {
        compositeDisposable.add(
            repository.deleteNotice(notice)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { showProgress() }
                .doAfterTerminate { hideProgress() }
                .subscribe({
                }, {
                    Log.d(TAG, "" + it)
                })
        )
    }

    companion object {
        const val TAG = "FavoriteViewModel"
    }
}