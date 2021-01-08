package com.mtjin.cnunoticeapp.views.photo_zoom

import android.os.Bundle
import com.bumptech.glide.Glide
import com.mtjin.cnunoticeapp.R
import com.mtjin.cnunoticeapp.base.BaseActivity
import com.mtjin.cnunoticeapp.databinding.ActivityPhotoZoomBinding
import com.mtjin.cnunoticeapp.utils.constants.EXTRA_IMAGE_URL

class PhotoZoomActivity : BaseActivity<ActivityPhotoZoomBinding>(R.layout.activity_photo_zoom) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        processIntent()
    }

    private fun processIntent() {
        val imageUrl = intent.getStringExtra(EXTRA_IMAGE_URL)
        Glide.with(this).load(imageUrl)
            .thumbnail(0.1f)
            .into(binding.ivPhotoView)
    }
}