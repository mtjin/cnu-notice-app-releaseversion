package com.mtjin.cnunoticeapp.views.employ

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mtjin.cnunoticeapp.base.BaseViewModel
import com.mtjin.cnunoticeapp.data.employ.EmployNotice
import com.mtjin.cnunoticeapp.data.employ.source.EmployNoticeRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class EmployNoticeViewModel(private val repository: EmployNoticeRepository) :
    BaseViewModel() {
    private val _noticeList = MutableLiveData<ArrayList<EmployNotice>>()

    val noticeList: LiveData<ArrayList<EmployNotice>> get() = _noticeList

    fun requestNotice() {
        compositeDisposable.add(
            repository.requestNotice()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { showProgress() }
                .doAfterTerminate { hideProgress() }
                .subscribe({
                    _noticeList.value = it as ArrayList<EmployNotice>?
                }, {
                    Log.d(TAG, "" + it)
                })
        )
    }

    fun requestMoreNotice(offset: Int) {
        compositeDisposable.add(
            repository.requestMoreNotice(offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { showProgress() }
                .doAfterTerminate { hideProgress() }
                .subscribe({ notices ->
                    val pagingNoticeList = _noticeList.value
                    pagingNoticeList?.addAll(notices)
                    _noticeList.value = pagingNoticeList
                }, {
                    Log.d(TAG, "" + it)
                })
        )
    }

    companion object {
        const val TAG = "EmployNoticeViewModel"
    }
}