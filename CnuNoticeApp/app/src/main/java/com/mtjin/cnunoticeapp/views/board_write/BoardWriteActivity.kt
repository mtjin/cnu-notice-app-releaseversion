package com.mtjin.cnunoticeapp.views.board_write

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
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
                showToast("길게 누르면 다중 사진선택이 가능합니다. (최대 9장)")
                val intent = Intent()
                intent.type = "image/*"
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
                intent.action = Intent.ACTION_GET_CONTENT
                startActivityForResult(
                    Intent.createChooser(intent, "사진 최대 9장 선택가능"),
                    RC_PICK_IMAGE
                )
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
            if (data?.clipData != null) { // 사진 여러개 선택한 경우
                val count = data.clipData!!.itemCount
                if (count > 9) {
                    showToast(getString(R.string.max_image_select_msg))
                    return
                }
                viewModel.imageUriList.clear()
                for (i in 0 until count) {
                    val imageUri = data.clipData!!.getItemAt(i).uri
                    viewModel.imageUriList.add(imageUri)
                }
                binding.ivImage.setImageURI(data.clipData!!.getItemAt(0).uri)
                if (count > 1) {
                    binding.tvImageCount.visibility = View.VISIBLE
                    binding.tvImageCount.text = "외 " + (count - 1) + "장"
                } else {
                    binding.tvImageCount.visibility = View.GONE
                }
            } else {
                data?.data?.let { uri ->
                    viewModel.imageUriList.clear()
                    viewModel.imageUriList.add(uri)
                    binding.ivImage.setImageURI(uri)
                    binding.tvImageCount.visibility = View.GONE
                }
            }
        }
    }

}