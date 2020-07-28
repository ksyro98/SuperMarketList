package com.example.supermarketlist.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.supermarketlist.R
import com.example.supermarketlist.data.ItemsViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var itemsViewModel: ItemsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = ListAdapter { itemId ->
            itemsViewModel.delete(itemId)
        }

        recyclerView.apply{
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            this.adapter = adapter
        }

        itemsViewModel = ViewModelProvider(this).get(ItemsViewModel::class.java)

        itemsViewModel.allItems.observe(this, Observer { items ->
            items?.let { adapter.setItems(it) }
        })


        addFab.setOnClickListener {
            val intent = Intent(this, AddItemActivity::class.java)
            startActivityForResult(intent, ADD_ITEM_ACT)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ADD_ITEM_ACT && resultCode == Activity.RESULT_OK){
            if(data != null &&
                data.hasExtra(ITEM_EXTRA_NAME) &&
                data.getStringExtra(ITEM_EXTRA_NAME) != ""){

                val item = data.getStringExtra(ITEM_EXTRA_NAME) ?: return
                itemsViewModel.insert(item)
            }
        }
    }
}