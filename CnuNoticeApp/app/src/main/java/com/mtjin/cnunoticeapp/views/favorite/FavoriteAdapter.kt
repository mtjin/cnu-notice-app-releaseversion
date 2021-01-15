package com.mtjin.cnunoticeapp.views.employ

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mtjin.cnunoticeapp.R
import com.mtjin.cnunoticeapp.data.favorite.FavoriteNotice
import com.mtjin.cnunoticeapp.databinding.ItemFavoriteBinding

class FavoriteAdapter(
    private val itemClick: (FavoriteNotice) -> Unit,
    private val numClick: (FavoriteNotice) -> Unit
) :
    RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    private val items: ArrayList<FavoriteNotice> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemFavoriteBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_favorite,
            parent,
            false
        )
        val viewHolder = ViewHolder(binding)
        binding.apply {
            root.setOnClickListener {
                itemClick(items[viewHolder.bindingAdapterPosition])
            }
            favoriteTvNum.setOnClickListener {
                numClick(items[viewHolder.bindingAdapterPosition])
            }
        }
        return viewHolder
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        items[position].let {
            holder.bind(it)
        }
    }

    class ViewHolder(private val binding: ItemFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: FavoriteNotice) {
            binding.item = item
            binding.executePendingBindings()
        }
    }

    fun addItems(items: List<FavoriteNotice>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun clear() {
        this.items.clear()
        notifyDataSetChanged()
    }

}