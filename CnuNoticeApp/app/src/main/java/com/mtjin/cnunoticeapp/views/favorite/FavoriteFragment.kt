package com.mtjin.cnunoticeapp.views.favorite

import android.content.Intent
import android.os.Bundle
import com.mtjin.cnunoticeapp.R
import com.mtjin.cnunoticeapp.base.BaseFragment
import com.mtjin.cnunoticeapp.constants.EXTRA_NOTICE_DELETE
import com.mtjin.cnunoticeapp.constants.EXTRA_NOTICE_LINK
import com.mtjin.cnunoticeapp.constants.EXTRA_NOTICE_SAVE
import com.mtjin.cnunoticeapp.constants.TAG_DIALOG_EVENT
import com.mtjin.cnunoticeapp.data.favorite.FavoriteNotice
import com.mtjin.cnunoticeapp.databinding.FragmentFavoriteBinding
import com.mtjin.cnunoticeapp.utils.NetworkManager
import com.mtjin.cnunoticeapp.views.dialog.DialogAddFragment
import com.mtjin.cnunoticeapp.views.employ.FavoriteAdapter
import com.mtjin.cnunoticeapp.views.notice_webview.NoticeWebViewActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment :
    BaseFragment<FragmentFavoriteBinding, FavoriteViewModel>(R.layout.fragment_favorite) {
    private lateinit var noticeAdapter: FavoriteAdapter
    override val viewModel: FavoriteViewModel by viewModel()

    private fun initAdapter() {
        noticeAdapter = FavoriteAdapter(itemClick = { item ->
            val intent = Intent(context, NoticeWebViewActivity::class.java)
            intent.putExtra(EXTRA_NOTICE_LINK, item.link)
            startActivity(intent)
        },
            numClick = {
                val bundle = Bundle()
                bundle.putParcelable(EXTRA_NOTICE_SAVE, FavoriteNotice(it.num, it.title, it.link))
                bundle.putString(EXTRA_NOTICE_DELETE, EXTRA_NOTICE_DELETE)
                val dialog: DialogAddFragment = DialogAddFragment().getInstance()
                dialog.arguments = bundle
                activity?.supportFragmentManager?.let { fragmentManager ->
                    dialog.show(
                        fragmentManager,
                        TAG_DIALOG_EVENT
                    )
                }
            })
        binding.rvBusiness.adapter = noticeAdapter
    }

    override fun init() {
        binding.vm = viewModel
        initAdapter()
        val networkManager: NetworkManager? = context?.let { NetworkManager(it) }
        if (!networkManager?.checkNetworkState()!!) {
            showToast(getString(R.string.network_err_toast))
        }
        viewModel.requestNotice()
    }
}