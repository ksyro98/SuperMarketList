package com.example.supermarketlist.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.supermarketlist.R
import com.example.supermarketlist.data.ItemsViewModel
import com.example.supermarketlist.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val itemsViewModel by viewModels<ItemsViewModel>()
    @Inject lateinit var itemsAdapter: ListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.apply{
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            this.adapter = itemsAdapter
        }

        itemsAdapter.setOnClick { itemId ->
            itemsViewModel.delete(itemId)
        }


        itemsViewModel.allItems.observe(this, Observer { items ->
            items?.let { itemsAdapter.setItems(it) }
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