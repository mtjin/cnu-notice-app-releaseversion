package com.mtjin.cnunoticeapp.views.notice

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.firebase.analytics.FirebaseAnalytics
import com.mtjin.cnunoticeapp.R
import com.mtjin.cnunoticeapp.base.BaseActivity
import com.mtjin.cnunoticeapp.databinding.ActivityNoticeBinding

class NoticeActivity : BaseActivity<ActivityNoticeBinding>(R.layout.activity_notice) {
    private var mFirebaseAnalytics: FirebaseAnalytics? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        initNavigation()
    }

    private fun initNavigation() {
        val navController = findNavController(R.id.main_nav_host)
        binding.mainBottomNavigation.setupWithNavController(navController)
    }
}
