package com.mtjin.cnunoticeapp.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {
    protected val compositeDisposable = CompositeDisposable()

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _isLottieLoading = MutableLiveData<Boolean>(false)
    val isLottieLoading: LiveData<Boolean> get() = _isLottieLoading

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    protected fun showProgress() {
        _isLoading.value = true
    }

    protected fun hideProgress() {
        _isLoading.value = false
    }

    fun showLottieProgress() {
        _isLottieLoading.value = true
    }

    fun hideLottieProgress() {
        _isLottieLoading.value = false
    }
}