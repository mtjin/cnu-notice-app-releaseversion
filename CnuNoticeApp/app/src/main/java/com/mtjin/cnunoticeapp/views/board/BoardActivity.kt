package com.mtjin.cnunoticeapp.views.board

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.mtjin.cnunoticeapp.R
import com.mtjin.cnunoticeapp.base.BaseActivity
import com.mtjin.cnunoticeapp.databinding.ActivityBoardBinding
import com.mtjin.cnunoticeapp.utils.constants.EXTRA_BOARD_NAME
import com.mtjin.cnunoticeapp.views.board_list.BoardListActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class BoardActivity : BaseActivity<ActivityBoardBinding>(R.layout.activity_board) {
    private val viewModel: BoardViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel
        initViewModelCallback()
        requestAlreadyAuth()
    }

    private fun initViewModelCallback() {
        with(viewModel) {
            univAuthSuccess.observe(this@BoardActivity, Observer { success ->
                if (success) {
                    binding.layoutUnivAuth.visibility = View.GONE
                    binding.layoutBoard.visibility = View.VISIBLE
                } else showToast(getString(R.string.univ_auth_fail_msg))
            })

            alreadyUnivAuth.observe(this@BoardActivity, Observer { isAlreadyAuth ->
                if (isAlreadyAuth) {
                    binding.layoutUnivAuth.visibility = View.GONE
                    binding.layoutBoard.visibility = View.VISIBLE
                }
            })

            goBoardList.observe(this@BoardActivity, Observer { boardName ->
                val intent = Intent(this@BoardActivity, BoardListActivity::class.java)
                intent.putExtra(EXTRA_BOARD_NAME, boardName)
                startActivity(intent)
            })
        }
    }

    //이미 대학교 인증했는지
    private fun requestAlreadyAuth() {
        viewModel.requestAlreadyAuth()
    }
}