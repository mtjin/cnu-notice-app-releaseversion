package com.mtjin.cnunoticeapp.views.favorite

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.mtjin.cnunoticeapp.R
import com.mtjin.cnunoticeapp.constants.EXTRA_NOTICE_DELETE
import com.mtjin.cnunoticeapp.constants.EXTRA_NOTICE_SAVE
import com.mtjin.cnunoticeapp.constants.TAG_DIALOG_EVENT
import com.mtjin.cnunoticeapp.data.favorite.FavoriteNotice
import com.mtjin.cnunoticeapp.databinding.FragmentFavoriteBinding
import com.mtjin.cnunoticeapp.utils.NetworkManager
import com.mtjin.cnunoticeapp.views.dialog.DialogAddFragment
import com.mtjin.cnunoticeapp.views.employ.FavoriteAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var noticeAdapter: FavoriteAdapter
    private val viewModel: FavoriteViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite, container, false)
        binding.lifecycleOwner = this
        binding.vm = viewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initAdapter()
        val networkManager: NetworkManager? = context?.let { NetworkManager(it) }
        if (!networkManager?.checkNetworkState()!!) {
            showToast(getString(R.string.network_err_toast))
        }
        viewModel.requestNotice()
    }

    private fun initAdapter() {
        noticeAdapter = FavoriteAdapter(itemClick = { item ->
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse(item.link)
            ).run(this::startActivity)
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

    private fun showToast(msg: String) =
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}