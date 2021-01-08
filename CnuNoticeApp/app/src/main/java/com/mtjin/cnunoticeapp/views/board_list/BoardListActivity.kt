package com.mtjin.cnunoticeapp.views.board_list

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.mtjin.cnunoticeapp.R
import com.mtjin.cnunoticeapp.base.BaseActivity
import com.mtjin.cnunoticeapp.databinding.ActivityBoardListBinding
import com.mtjin.cnunoticeapp.utils.constants.EXTRA_BOARD
import com.mtjin.cnunoticeapp.utils.constants.EXTRA_BOARD_NAME
import com.mtjin.cnunoticeapp.views.board_detail.BoardDetailActivity
import com.mtjin.cnunoticeapp.views.board_write.BoardWriteActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class BoardListActivity : BaseActivity<ActivityBoardListBinding>(R.layout.activity_board_list) {
    private val viewModel: BoardListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel
        processIntent()
        initViewModelCallback()
        initAdapter()
        requestData()
    }

    override fun onRestart() {
        requestData()
        super.onRestart()
    }

    private fun requestData() {
        viewModel.requestBoards(10)
    }

    private fun initAdapter() {
        binding.rvBoards.adapter = BoardAdapter(itemClick = { board ->
            val intent = Intent(this@BoardListActivity, BoardDetailActivity::class.java)
            intent.putExtra(EXTRA_BOARD, board)
            intent.putExtra(EXTRA_BOARD_NAME, viewModel.boardName.value)
            startActivity(intent)
        })
    }

    private fun initViewModelCallback() {
        with(viewModel) {
            goBoardWrite.observe(this@BoardListActivity, Observer {
                val intent = Intent(this@BoardListActivity, BoardWriteActivity::class.java)
                intent.putExtra(EXTRA_BOARD_NAME, viewModel.boardName.value)
                startActivity(intent)
            })
        }
    }

    private fun processIntent() {
        val boardName =
            intent.getStringExtra(EXTRA_BOARD_NAME)
                ?: throw IllegalArgumentException(getString(R.string.no_extra_value_exception))
        viewModel.setBoardName(boardName)
    }
}