package com.mtjin.cnunoticeapp.views.business

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mtjin.cnunoticeapp.R
import com.mtjin.cnunoticeapp.data.business.BusinessNotice
import com.mtjin.cnunoticeapp.databinding.ItemBusinessBinding

class BusinessAdapter(
    private val itemClick: (BusinessNotice) -> Unit,
    private val numClick: (BusinessNotice) -> Unit
) :
    RecyclerView.Adapter<BusinessAdapter.ViewHolder>() {

    private val items: ArrayList<BusinessNotice> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusinessAdapter.ViewHolder {
        val binding: ItemBusinessBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_business,
            parent,
            false
        )
        val viewHolder = ViewHolder(binding)
        binding.root.setOnClickListener {
            itemClick(items[viewHolder.bindingAdapterPosition])
        }
        binding.businessTvNum.setOnClickListener {
            numClick(items[viewHolder.bindingAdapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: BusinessAdapter.ViewHolder, position: Int) {
        items[position].let {
            holder.bind(it)
        }
    }

    class ViewHolder(private val binding: ItemBusinessBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: BusinessNotice) {
            binding.item = item
            binding.executePendingBindings()
        }
    }

    fun addItems(items: List<BusinessNotice>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun clear() {
        this.items.clear()
        notifyDataSetChanged()
    }

}