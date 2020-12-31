package com.mtjin.cnunoticeapp.views.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.mtjin.cnunoticeapp.R
import com.mtjin.cnunoticeapp.utils.constants.EXTRA_NOTICE_DELETE
import com.mtjin.cnunoticeapp.utils.constants.EXTRA_NOTICE_SAVE
import com.mtjin.cnunoticeapp.data.favorite.FavoriteNotice
import com.mtjin.cnunoticeapp.views.favorite.FavoriteViewModel
import kotlinx.android.synthetic.main.fragment_dialog_add.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DialogAddFragment : DialogFragment(), View.OnClickListener {
    private val viewModel: FavoriteViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dialog_add, container, false)
        processBundle(view)
        return view
    }

    private fun processBundle(view: View) {
        val bundle = arguments
        val notice = bundle?.getParcelable<FavoriteNotice>(EXTRA_NOTICE_SAVE)
        when (bundle?.getString(EXTRA_NOTICE_DELETE, "")) {
            EXTRA_NOTICE_DELETE -> {
                view.dialog_tv_question.text = getString(R.string.dialog_favorite_delete_text)
                view.dialog_tv_yes.setOnClickListener {
                    notice?.let { notice ->
                        viewModel.delete(notice)
                        showToast(getString(R.string.delete_success_text))
                        dismiss()
                    }
                }
                view.dialog_tv_no.setOnClickListener {
                    dismiss()
                }
            }
            else -> {
                view.dialog_tv_yes.setOnClickListener {
                    notice?.let { notice ->
                        viewModel.insert(notice)
                        showToast(getString(R.string.add_success_text))
                        dismiss()
                    }
                }
                view.dialog_tv_no.setOnClickListener {
                    dismiss()
                }
            }
        }
    }

    fun showToast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    fun getInstance(): DialogAddFragment {
        return DialogAddFragment()
    }

    override fun onClick(p0: View?) {
        dismiss()
    }

}