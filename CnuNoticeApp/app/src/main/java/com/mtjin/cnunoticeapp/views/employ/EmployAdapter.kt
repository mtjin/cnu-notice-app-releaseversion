package com.mtjin.cnunoticeapp.views.employ

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mtjin.cnunoticeapp.R
import com.mtjin.cnunoticeapp.data.employ.EmployNotice
import com.mtjin.cnunoticeapp.databinding.ItemEmployBinding

class EmployAdapter(
    private val itemClick: (EmployNotice) -> Unit,
    private val numClick: (EmployNotice) -> Unit
) :
    RecyclerView.Adapter<EmployAdapter.ViewHolder>() {

    private val items: ArrayList<EmployNotice> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemEmployBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_employ,
            parent,
            false
        )
        val viewHolder = ViewHolder(binding)
        binding.root.setOnClickListener {
            itemClick(items[viewHolder.adapterPosition])
        }
        binding.employTvNum.setOnClickListener {
            numClick(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        items[position].let {
            holder.bind(it)
        }
    }

    class ViewHolder(private val binding: ItemEmployBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: EmployNotice) {
            binding.item = item
            binding.executePendingBindings()
        }
    }

    fun addItems(items: List<EmployNotice>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun clear() {
        this.items.clear()
        notifyDataSetChanged()
    }

}