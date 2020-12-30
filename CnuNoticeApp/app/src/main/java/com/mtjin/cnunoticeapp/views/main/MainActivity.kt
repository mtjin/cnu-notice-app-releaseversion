package com.mtjin.cnunoticeapp.views.main

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.mtjin.cnunoticeapp.R
import com.mtjin.cnunoticeapp.base.BaseActivity
import com.mtjin.cnunoticeapp.databinding.ActivityMainBinding
import com.mtjin.cnunoticeapp.views.board.BoardActivity
import com.mtjin.cnunoticeapp.views.notice.NoticeActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel
        initViewModelCallback()
    }

    private fun initViewModelCallback() {
        with(viewModel) {
            goNotice.observe(this@MainActivity, Observer {
                val intent = Intent(this@MainActivity, NoticeActivity::class.java)
                startActivity(intent)
            })

            goBoard.observe(this@MainActivity, Observer {
                val intent = Intent(this@MainActivity, BoardActivity::class.java)
                startActivity(intent)
            })
        }
    }
}