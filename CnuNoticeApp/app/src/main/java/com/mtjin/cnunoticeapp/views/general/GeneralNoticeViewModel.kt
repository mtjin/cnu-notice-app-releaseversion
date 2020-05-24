package com.mtjin.cnunoticeapp.views.general

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mtjin.cnunoticeapp.base.BaseViewModel
import com.mtjin.cnunoticeapp.data.general.GeneralNotice
import com.mtjin.cnunoticeapp.data.general.source.GeneralNoticeRepository
import com.mtjin.cnunoticeapp.data.general.source.remote.GeneralNoticeRemoteDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GeneralNoticeViewModel(private val generalRepository: GeneralNoticeRepository) :
    BaseViewModel() {
    private val _noticeList = MutableLiveData<ArrayList<GeneralNotice>>()

    val noticeList: LiveData<ArrayList<GeneralNotice>> get() = _noticeList

    fun requestNotice() {
        compositeDisposable.add(
            generalRepository.requestNotice()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { showProgress() }
                .doAfterTerminate { hideProgress() }
                .subscribe({
                    _noticeList.value = it as ArrayList<GeneralNotice>?
                }, {
                    Log.d(TAG, "" + it)
                })
        )
    }

    fun requestMoreNotice(offset: Int) {
        compositeDisposable.add(
            generalRepository.requestMoreNotice(offset)
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
        const val TAG = "GeneralNoticeViewModel"
    }
}