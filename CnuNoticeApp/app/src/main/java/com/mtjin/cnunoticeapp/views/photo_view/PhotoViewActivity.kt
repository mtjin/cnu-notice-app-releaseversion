package com.mtjin.cnunoticeapp.views.photo_view

import android.os.Bundle
import com.mtjin.cnunoticeapp.R
import com.mtjin.cnunoticeapp.base.BaseActivity
import com.mtjin.cnunoticeapp.databinding.ActivityPhotoViewBinding
import com.mtjin.cnunoticeapp.utils.constants.EXTRA_IMAGE_POSITION
import com.mtjin.cnunoticeapp.utils.constants.EXTRA_IMAGE_URLS

class PhotoViewActivity : BaseActivity<ActivityPhotoViewBinding>(R.layout.activity_photo_view) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        processIntent()
    }


    private fun processIntent() {
        intent.run {
            getStringArrayListExtra(EXTRA_IMAGE_URLS)?.let { imageUrls ->
                binding.vpViewpager.adapter =
                    PhotoViewPagerAdapter(this@PhotoViewActivity, imageUrls)
            }
            getIntExtra(EXTRA_IMAGE_POSITION, 0).let { position ->
                binding.vpViewpager.currentItem = position
            }
        }

    }
}