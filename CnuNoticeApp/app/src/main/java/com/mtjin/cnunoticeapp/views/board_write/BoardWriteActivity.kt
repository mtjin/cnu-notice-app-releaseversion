package com.mtjin.cnunoticeapp.views.board_write

import android.os.Bundle
import com.mtjin.cnunoticeapp.R
import com.mtjin.cnunoticeapp.base.BaseActivity
import com.mtjin.cnunoticeapp.databinding.ActivityBoardWriteBinding
import com.mtjin.cnunoticeapp.utils.constants.EXTRA_BOARD_NAME
import org.koin.androidx.viewmodel.ext.android.viewModel

class BoardWriteActivity : BaseActivity<ActivityBoardWriteBinding>(R.layout.activity_board_write) {
    private val viewModel: BoardWriteViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel
        initViewModelCallback()
        processIntent()
    }

    private fun initViewModelCallback() {
        with(viewModel) {

        }
    }

    private fun processIntent() {
        val boardName =
            intent.getStringExtra(EXTRA_BOARD_NAME)
                ?: throw IllegalArgumentException(getString(R.string.no_extra_value_exception))
        viewModel.boardName = boardName
    }
}