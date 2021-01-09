package com.mtjin.cnunoticeapp.views.board_detail

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.mtjin.cnunoticeapp.R
import com.mtjin.cnunoticeapp.base.BaseActivity
import com.mtjin.cnunoticeapp.data.board_list.Board
import com.mtjin.cnunoticeapp.databinding.ActivityBoardDetailBinding
import com.mtjin.cnunoticeapp.utils.constants.EXTRA_BOARD
import com.mtjin.cnunoticeapp.utils.constants.EXTRA_BOARD_NAME
import com.mtjin.cnunoticeapp.utils.constants.EXTRA_IMAGE_URL
import com.mtjin.cnunoticeapp.utils.constants.uuid
import com.mtjin.cnunoticeapp.views.dialog.YesNoDialogFragment
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
        initArrays()
        initEvent()
        initViewModelCallback()
        requestComments()
    }

    private fun initViewModelCallback() {
        with(viewModel) {
            boardRecommendResult.observe(this@BoardDetailActivity, Observer { success ->
                if (!success) showToast(getString(R.string.recommend_fail_msg))
                else binding.tvRecommendCount.text =
                    ((binding.tvRecommendCount.text.toString().toInt() + 1).toString())
            })

            commentRecommendResult.observe(this@BoardDetailActivity, Observer {success ->
                if (!success) showToast(getString(R.string.recommend_fail_msg))
            })
        }
    }

    private fun initEvent() {
        binding.tvRecommendCount.setOnClickListener {// 게시물 추천 클릭 시 네, 아니요 다이얼로그
            val dialog =
                YesNoDialogFragment.getInstance(yesClick = {
                    if (it) {
                        if (viewModel.board.recommendList.contains(uuid)) { //이미 내가 추천한 게시물
                            showToast(getString(R.string.already_recommend_board_msg))
                        } else {
                            viewModel.updateBoardRecommend()
                        }
                    }
                })
            dialog.show(this.supportFragmentManager, dialog.tag)
        }
    }

    private fun requestComments() {
        viewModel.requestComments()
    }

    private fun initArrays() {
        viewModel.firstNameList = resources.getStringArray(R.array.first_nicknames)
        viewModel.secondNameList = resources.getStringArray(R.array.second_nicknames)
    }

    private fun initAdapter() {
        //이미지 어댑터
        binding.rvImages.adapter = BoardImageAdapter(itemClick = { imageUrl ->
            val intent = Intent(this, PhotoZoomActivity::class.java)
            intent.putExtra(EXTRA_IMAGE_URL, imageUrl)
            startActivity(intent)
        })

        //댓글 어댑터
        binding.rvComments.adapter = BoardCommentAdapter(itemClick = { comment ->
            val dialog =
                YesNoDialogFragment.getInstance(yesClick = {
                    if (it) {
                        if (comment.recommendList.contains(uuid)) { //이미 내가 추천한 게시물
                            showToast(getString(R.string.already_recommend_board_msg))
                        } else {
                            viewModel.updateCommentRecommend(comment = comment)
                        }
                    }
                })
            dialog.show(this.supportFragmentManager, dialog.tag)
        })
    }

    private fun processIntent() {
        val board = intent.getParcelableExtra<Board>(EXTRA_BOARD)
        val boardName = intent.getStringExtra(EXTRA_BOARD_NAME)
        binding.item = board
        viewModel.board = board!!
        viewModel.boardName = boardName!!
    }
}