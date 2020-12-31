package com.mtjin.cnunoticeapp.views.favorite

import android.content.Intent
import android.os.Bundle
import android.text.util.Linkify
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
import java.util.regex.Matcher
import java.util.regex.Pattern

class FavoriteFragment :
    BaseFragment<FragmentFavoriteBinding>(R.layout.fragment_favorite) {
    private lateinit var noticeAdapter: FavoriteAdapter
    val viewModel: FavoriteViewModel by viewModel()

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
        initLinkfy()
    }

    fun initLinkfy() {
        val transform =
            Linkify.TransformFilter(object : Linkify.TransformFilter, (Matcher, String) -> String {
                override fun transformUrl(p0: Matcher?, p1: String?): String {
                    return ""
                }

                override fun invoke(p1: Matcher, p2: String): String {
                    return ""
                }

            })
        //링크달 패턴 정의
        val pattern1 = Pattern.compile("https://github.com/mtjin/cnu-notice-app-releaseversion")
        Linkify.addLinks(
            binding.favoriteTvExplain,
            pattern1,
            "https://github.com/mtjin/cnu-notice-app-releaseversion",
            null,
            transform
        )


    }
}