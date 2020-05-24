package com.mtjin.cnunoticeapp.views.bachelor

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mtjin.cnunoticeapp.base.BaseViewModel
import com.mtjin.cnunoticeapp.data.bachelor.BachelorNotice
import com.mtjin.cnunoticeapp.data.bachelor.source.BachelorNoticeRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class BachelorNoticeViewModel(private val bachelorRepository: BachelorNoticeRepository) :
    BaseViewModel() {
    private val _noticeList = MutableLiveData<ArrayList<BachelorNotice>>()

    val noticeList: LiveData<ArrayList<BachelorNotice>> get() = _noticeList

    fun requestNotice() {
        compositeDisposable.add(
            bachelorRepository.requestNotice()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { showProgress()}
                .doAfterTerminate { hideProgress()}
                .subscribe({
                    _noticeList.value = it as ArrayList<BachelorNotice>?
                }, {
                    Log.d(TAG, "" + it)
                })
        )
    }

    fun requestMoreNotice(offset: Int) {
        compositeDisposable.add(
            bachelorRepository.requestMoreNotice(offset)
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
        const val TAG = "BachelorNoticeViewModel"
    }
}