package com.mtjin.cnunoticeapp.views.board_detail

import android.content.Intent
import android.os.Bundle
import com.mtjin.cnunoticeapp.R
import com.mtjin.cnunoticeapp.base.BaseActivity
import com.mtjin.cnunoticeapp.data.board_list.Board
import com.mtjin.cnunoticeapp.databinding.ActivityBoardDetailBinding
import com.mtjin.cnunoticeapp.utils.constants.EXTRA_BOARD
import com.mtjin.cnunoticeapp.utils.constants.EXTRA_BOARD_NAME
import com.mtjin.cnunoticeapp.utils.constants.EXTRA_IMAGE_URL
import com.mtjin.cnunoticeapp.views.photo_zoom.PhotoZoomActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class BoardDetailActivity :
    BaseActivity<ActivityBoardDetailBinding>(R.layout.activity_board_detail) {
    private val viewModel: BoardDetailViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel
        initAdapter()
        processIntent()
    }

    private fun initAdapter() {
        binding.rvImages.adapter = BoardImageAdapter(itemClick = { imageUrl ->
            val intent = Intent(this, PhotoZoomActivity::class.java)
            intent.putExtra(EXTRA_IMAGE_URL, imageUrl)
            startActivity(intent)
        })
    }

    private fun processIntent() {
        val board = intent.getParcelableExtra<Board>(EXTRA_BOARD)
        val boardName = intent.getStringExtra(EXTRA_BOARD_NAME)
        binding.item = board
        viewModel.boardName = boardName!!
    }
}