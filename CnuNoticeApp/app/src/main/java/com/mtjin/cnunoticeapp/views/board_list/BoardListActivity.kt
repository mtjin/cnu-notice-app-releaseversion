package com.mtjin.cnunoticeapp.views.board_list

import android.os.Bundle
import com.mtjin.cnunoticeapp.R
import com.mtjin.cnunoticeapp.base.BaseActivity
import com.mtjin.cnunoticeapp.databinding.ActivityBoardListBinding
import com.mtjin.cnunoticeapp.utils.constants.EXTRA_BOARD_NAME

class BoardListActivity : BaseActivity<ActivityBoardListBinding>(R.layout.activity_board_list) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        processIntent()
    }

    private fun processIntent() {
        val boardName = intent.getStringExtra(EXTRA_BOARD_NAME)
        binding.tvTitle.text = boardName
    }
}