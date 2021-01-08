package com.mtjin.cnunoticeapp.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.mtjin.cnunoticeapp.utils.SharedPrefManager
import com.mtjin.cnunoticeapp.utils.constants.uuid
import com.mtjin.cnunoticeapp.views.dialog.LottieDialogFragment
import io.reactivex.disposables.CompositeDisposable

abstract class BaseActivity<B : ViewDataBinding>(
    @LayoutRes val layoutId: Int
) : AppCompatActivity() {
    lateinit var binding: B
    private val compositeDisposable = CompositeDisposable()
    lateinit var lottieDialog: LottieDialogFragment

    override fun onStart() {
        uuid = SharedPrefManager(this).uuid
        super.onStart()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.lifecycleOwner = this
        lottieDialog = LottieDialogFragment.newInstance()
    }

    protected fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    fun showProgressDialog() {
        if (!lottieDialog.isAdded) lottieDialog.show(
            this.supportFragmentManager,
            lottieDialog.tag
        )
    }

    fun hideProgressDialog() {
        if (lottieDialog.isAdded) {
            lottieDialog.dismissAllowingStateLoss()
        }
    }
}

