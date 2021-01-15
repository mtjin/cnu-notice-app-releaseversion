package com.mtjin.cnunoticeapp.views.employ

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mtjin.cnunoticeapp.R
import com.mtjin.cnunoticeapp.data.employ.EmployNotice
import com.mtjin.cnunoticeapp.databinding.ItemEmployBinding

class EmployAdapter(
    private val itemClick: (EmployNotice) -> Unit,
    private val numClick: (EmployNotice) -> Unit
) : ListAdapter<EmployNotice, EmployAdapter.ViewHolder>(
    diffUtil
) {
    //리스트 선언 필요X
    //private val items = mutableListOf<EmployNotice>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemEmployBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_employ,
            parent,
            false
        )
        val viewHolder = ViewHolder(binding)
        binding.apply {
            root.setOnClickListener {
                itemClick(getItem(viewHolder.bindingAdapterPosition)) //getItem()으로 아이템 가져옴
            }
            employTvNum.setOnClickListener {
                numClick(getItem(viewHolder.bindingAdapterPosition))
            }
        }
        return viewHolder
    }

    //getItemCount() 오버라이딩 메서드 사라짐
    //override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)) //변경된 점 -> getItem(position) 메서드가 생겼다.
    }

    class ViewHolder(private val binding: ItemEmployBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: EmployNotice) {
            binding.item = item
            binding.executePendingBindings()
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<EmployNotice>() {
            override fun areContentsTheSame(oldItem: EmployNotice, newItem: EmployNotice) =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: EmployNotice, newItem: EmployNotice) =
                oldItem.link == newItem.link
        }
    }
}