package com.mtjin.cnunoticeapp.views.bachelor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mtjin.cnunoticeapp.R
import com.mtjin.cnunoticeapp.data.bachelor.BachelorNotice
import com.mtjin.cnunoticeapp.databinding.ItemBachelorBinding

class BachelorAdapter(
    private val itemClick: (BachelorNotice) -> Unit,
    private val numClick: (BachelorNotice) -> Unit
) :
    RecyclerView.Adapter<BachelorAdapter.ViewHolder>() {

    private val items: ArrayList<BachelorNotice> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BachelorAdapter.ViewHolder {
        val binding: ItemBachelorBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_bachelor,
            parent,
            false
        )
        val viewHolder = ViewHolder(binding)
        binding.apply {
            root.setOnClickListener {
                itemClick(items[viewHolder.bindingAdapterPosition])
            }
            bachelorTvNum.setOnClickListener {
                numClick(items[viewHolder.bindingAdapterPosition])
            }
        }
        return viewHolder
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: BachelorAdapter.ViewHolder, position: Int) {
        items[position].let {
            holder.bind(it)
        }
    }

    class ViewHolder(private val binding: ItemBachelorBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: BachelorNotice) {
            binding.item = item
            binding.executePendingBindings()
        }
    }

    fun addItems(items: List<BachelorNotice>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun clear() {
        this.items.clear()
        notifyDataSetChanged()
    }

}