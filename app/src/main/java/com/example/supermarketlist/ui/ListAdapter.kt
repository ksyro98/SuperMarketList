package com.example.supermarketlist.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.supermarketlist.R
import com.example.supermarketlist.data.Item
import javax.inject.Inject


class ListAdapter @Inject constructor() : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    private var items = emptyList<Item>()
    private lateinit var onClick: (clickedItemId: Int) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)

        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.itemTextView.text = items[position].itemName
        holder.itemTextView.setOnClickListener {
            if(::onClick.isInitialized) {
                onClick(items[position].id)
            }
        }
    }

    override fun getItemCount() = items.size

    fun setItems(items: List<Item>){
        this.items = items
        notifyDataSetChanged()
    }

    fun setOnClick(callback: (clickedItemId: Int) -> Unit){
        this.onClick = callback
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val itemTextView: TextView = itemView.findViewById(R.id.itemTextView)
    }

}