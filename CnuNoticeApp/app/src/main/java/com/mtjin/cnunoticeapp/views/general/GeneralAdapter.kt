package com.mtjin.cnunoticeapp.views.general

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mtjin.cnunoticeapp.R
import com.mtjin.cnunoticeapp.data.general.GeneralNotice
import com.mtjin.cnunoticeapp.databinding.ItemGeneralBinding

class GeneralAdapter(
    private val itemClick: (GeneralNotice) -> Unit,
    private val numClick: (GeneralNotice) -> Unit
) :
    RecyclerView.Adapter<GeneralAdapter.ViewHolder>() {

    private val items: ArrayList<GeneralNotice> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GeneralAdapter.ViewHolder {
        val binding: ItemGeneralBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_general,
            parent,
            false
        )
        val viewHolder = ViewHolder(binding)
        binding.apply {
            root.setOnClickListener {
                itemClick(items[viewHolder.bindingAdapterPosition])
            }
            generalTvNum.setOnClickListener {
                numClick(items[viewHolder.bindingAdapterPosition])
            }
        }
        return viewHolder
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: GeneralAdapter.ViewHolder, position: Int) {
        items[position].let {
            holder.bind(it)
        }
    }

    class ViewHolder(private val binding: ItemGeneralBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: GeneralNotice) {
            binding.item = item
            binding.executePendingBindings()
        }
    }

    fun addItems(items: List<GeneralNotice>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun clear() {
        this.items.clear()
        notifyDataSetChanged()
    }

}