package com.mtjin.cnunoticeapp.views.board_write

import android.Manifest
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import com.mtjin.cnunoticeapp.R
import com.mtjin.cnunoticeapp.base.BaseActivity
import com.mtjin.cnunoticeapp.databinding.ActivityBoardWriteBinding
import com.mtjin.cnunoticeapp.utils.constants.EXTRA_BOARD_NAME
import gun0912.tedimagepicker.builder.TedImagePicker
import org.koin.androidx.viewmodel.ext.android.viewModel


class BoardWriteActivity : BaseActivity<ActivityBoardWriteBinding>(R.layout.activity_board_write) {
    private val viewModel: BoardWriteViewModel by viewModel()
    private val RC_PICK_IMAGE = 1001
    lateinit var permissionlistener: PermissionListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel
        initViewModelCallback()
        initPermission()
        processIntent()
    }

    private fun initPermission() {
        permissionlistener = object : PermissionListener {
            override fun onPermissionGranted() {
                showToast("길게 누르면 다중 사진선택이 가능합니다. (최대 9장)")
                TedImagePicker.with(this@BoardWriteActivity)
                    .max(9, "최대 9장")
                    .startMultiImage { uriList ->
                        val count = uriList.size
                        if (count > 9) {
                            showToast(getString(R.string.max_image_select_msg))
                            return@startMultiImage
                        }
                        viewModel.imageUriList.clear()
                        for (i in 0 until count) {
                            val imageUri = uriList[i]
                            viewModel.imageUriList.add(imageUri)
                        }
                        binding.ivImage.setImageURI(uriList[0])
                        if (count > 1) {
                            binding.tvImageCount.visibility = View.VISIBLE
                            binding.tvImageCount.text = "외 " + (count - 1) + "장"
                        } else {
                            binding.tvImageCount.visibility = View.GONE
                        }
                    }
            }

            override fun onPermissionDenied(deniedPermissions: List<String>) {
                showToast("권한 거절\n$deniedPermissions")
            }
        }
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
                TedPermission.with(this@BoardWriteActivity)
                    .setPermissionListener(permissionlistener)
                    .setDeniedMessage("갤러리 접근권한 허용이 필요합니다.")
                    .setPermissions(
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    )
                    .check()
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

}