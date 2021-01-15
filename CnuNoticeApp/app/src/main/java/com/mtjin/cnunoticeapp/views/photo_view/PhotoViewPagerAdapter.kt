package com.mtjin.cnunoticeapp.views.photo_view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.chrisbanes.photoview.PhotoView
import com.mtjin.cnunoticeapp.R

class PhotoViewPagerAdapter(private val context: Context, private val items: ArrayList<String>) :
    RecyclerView.Adapter<PhotoViewPagerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_photo_view_pager, parent, false)
        )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(items[position]).into(holder.imagePhotoView)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imagePhotoView: PhotoView = view.findViewById(R.id.iv_photo_view)
    }
}