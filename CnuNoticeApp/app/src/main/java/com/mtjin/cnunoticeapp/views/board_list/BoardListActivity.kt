package com.mtjin.cnunoticeapp.views.board_list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import com.mtjin.cnunoticeapp.R
import com.mtjin.cnunoticeapp.base.BaseActivity
import com.mtjin.cnunoticeapp.databinding.ActivityBoardListBinding
import com.mtjin.cnunoticeapp.utils.constants.EXTRA_BOARD
import com.mtjin.cnunoticeapp.utils.constants.EXTRA_BOARD_NAME
import com.mtjin.cnunoticeapp.views.board_detail.BoardDetailActivity
import com.mtjin.cnunoticeapp.views.board_write.BoardWriteActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.PublishSubject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

class BoardListActivity : BaseActivity<ActivityBoardListBinding>(R.layout.activity_board_list) {
    private val viewModel: BoardListViewModel by viewModel()

    private val searchSubject = PublishSubject.create<String>() //자동검색 기능담당

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel
        processIntent()
        initViewModelCallback()
        initAdapter()
        initView()
        requestData()
    }

    private fun initView() {
        binding.etSearch.doOnTextChanged { text, _, _, _ ->
            searchSubject.onNext(text.toString())
        }
        compositeDisposable.add(
            searchSubject
                .observeOn(AndroidSchedulers.mainThread())
                .debounce(1000, TimeUnit.MILLISECONDS)
                .filter { it.isNotBlank() }
                .map { it.trim() }
                .subscribe { name ->
                    Log.d("검색한 게시물 -> %s", name)
                    viewModel.searchBoard(name = name, page = 10)
                }
        )
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

            clickSearch.observe(this@BoardListActivity, Observer { openSearch ->
                if (openSearch) {
                    binding.etSearch.visibility = View.VISIBLE
                    binding.etSearch.requestFocus()
                    if (binding.etSearch.requestFocus()) {
                        //키보드 보이게 하는 부분
                        val imm: InputMethodManager =
                            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.toggleSoftInput(
                            InputMethodManager.SHOW_FORCED,
                            InputMethodManager.HIDE_IMPLICIT_ONLY
                        )
                    }
                } else {
                    binding.etSearch.visibility = View.GONE
                    viewModel.requestBoards(10)
                }

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