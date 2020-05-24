package com.mtjin.cnunoticeapp.views.business

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mtjin.cnunoticeapp.base.BaseViewModel
import com.mtjin.cnunoticeapp.data.business.BusinessNotice
import com.mtjin.cnunoticeapp.data.business.source.BusinessNoticeRepository
import com.mtjin.cnunoticeapp.data.business.source.remote.BusinessNoticeRemoteDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class BusinessNoticeViewModel(private val repository: BusinessNoticeRepository) :
    BaseViewModel() {
    private val _noticeList = MutableLiveData<ArrayList<BusinessNotice>>()

    val noticeList: LiveData<ArrayList<BusinessNotice>> get() = _noticeList

    fun requestNotice() {
        compositeDisposable.add(
            repository.requestNotice()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { showProgress() }
                .doAfterTerminate { hideProgress() }
                .subscribe({
                    _noticeList.value = it as ArrayList<BusinessNotice>?
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
        const val TAG = "BusinessNoticeViewModel"
    }
}