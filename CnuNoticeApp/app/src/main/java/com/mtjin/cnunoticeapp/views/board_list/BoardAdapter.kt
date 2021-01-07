package com.mtjin.cnunoticeapp.views.board_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mtjin.cnunoticeapp.R
import com.mtjin.cnunoticeapp.data.board_list.Board
import com.mtjin.cnunoticeapp.databinding.ItemBoardBinding

class BoardAdapter(private val itemClick: (Board) -> Unit) :
    RecyclerView.Adapter<BoardAdapter.ViewHolder>() {
    private val items = mutableListOf<Board>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemBoardBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_board,
            parent,
            false
        )
        return ViewHolder(binding).apply {
            binding.root.setOnClickListener { view ->
                val position = adapterPosition.takeIf { it != RecyclerView.NO_POSITION }
                    ?: return@setOnClickListener
                itemClick(items[position])
            }
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        items[position].let {
            holder.bind(it)
        }
    }

    class ViewHolder(private val binding: ItemBoardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(board: Board) {
            binding.item = board
            binding.executePendingBindings()
        }
    }

    fun addItems(items: List<Board>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun addItem(item: Board) {
        this.items.add(item)
        notifyDataSetChanged()
    }

    fun clear() {
        this.items.clear()
        notifyDataSetChanged()
    }
}