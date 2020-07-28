package com.example.supermarketlist.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.supermarketlist.R
import com.example.supermarketlist.data.Item


class ListAdapter(val onClick: (clickedItemId: Int) -> Unit) :
    RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    private var items = emptyList<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)

        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.itemTextView.text = items[position].itemName
        holder.itemTextView.setOnClickListener {
            onClick(items[position].id)
        }
    }

    override fun getItemCount() = items.size

    fun setItems(items: List<Item>){
        this.items = items
        notifyDataSetChanged()
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val itemTextView: TextView = itemView.findViewById(R.id.itemTextView)
    }

}