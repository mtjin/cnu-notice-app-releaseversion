package com.mtjin.cnunoticeapp.views.board_write

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.mtjin.cnunoticeapp.R
import com.mtjin.cnunoticeapp.base.BaseActivity
import com.mtjin.cnunoticeapp.databinding.ActivityBoardWriteBinding
import com.mtjin.cnunoticeapp.utils.constants.EXTRA_BOARD_NAME
import org.koin.androidx.viewmodel.ext.android.viewModel

class BoardWriteActivity : BaseActivity<ActivityBoardWriteBinding>(R.layout.activity_board_write) {
    private val viewModel: BoardWriteViewModel by viewModel()
    private val RC_PICK_IMAGE = 1001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel
        initViewModelCallback()
        processIntent()
    }

    private fun initViewModelCallback() {
        with(viewModel) {
            emptyMsg.observe(this@BoardWriteActivity, Observer {
                showToast(getString(R.string.please_insert_content_msg))
            })

            insertBoardSuccess.observe(this@BoardWriteActivity, Observer { success ->
                if (!success) showToast(getString(R.string.write_err_msg))
                else {
                    showToast(getString(R.string.write_compelete_msg))
                    finish()
                }
            })

            pickImage.observe(this@BoardWriteActivity, Observer {
                val intent = Intent(Intent.ACTION_PICK)
                intent.type = "image/*"
                startActivityForResult(intent, RC_PICK_IMAGE)
            })

            isLottieLoading.observe(this@BoardWriteActivity, Observer { loading ->
                if (loading) showProgressDialog()
                else hideProgressDialog()
            })
        }
    }

    private fun processIntent() {
        val boardName =
            intent.getStringExtra(EXTRA_BOARD_NAME)
                ?: throw IllegalArgumentException(getString(R.string.no_extra_value_exception))
        viewModel.boardName = boardName
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == RC_PICK_IMAGE) {
            data?.data?.let {
                binding.ivImage.setImageURI(it)
                viewModel.imageUri = it
            }
        }
    }
}