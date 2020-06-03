package com.mtjin.cnunoticeapp.views.business

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.mtjin.cnunoticeapp.R
import com.mtjin.cnunoticeapp.base.BaseFragment
import com.mtjin.cnunoticeapp.constants.EXTRA_NOTICE_SAVE
import com.mtjin.cnunoticeapp.constants.TAG_DIALOG_EVENT
import com.mtjin.cnunoticeapp.data.favorite.FavoriteNotice
import com.mtjin.cnunoticeapp.databinding.FragmentBusinessBinding
import com.mtjin.cnunoticeapp.utils.NetworkManager
import com.mtjin.cnunoticeapp.views.dialog.DialogAddFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class BusinessNoticeFragment :
    BaseFragment<FragmentBusinessBinding, BusinessNoticeViewModel>(R.layout.fragment_business) {
    private lateinit var noticeAdapter: BusinessAdapter
    override val viewModel: BusinessNoticeViewModel by viewModel()

    private fun initAdapter() {
        noticeAdapter = BusinessAdapter(itemClick = { item ->
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse(item.link)
            ).run(this::startActivity)
        },
            numClick = {
                val bundle = Bundle()
                bundle.putParcelable(EXTRA_NOTICE_SAVE, FavoriteNotice(it.num, it.title, it.link))
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