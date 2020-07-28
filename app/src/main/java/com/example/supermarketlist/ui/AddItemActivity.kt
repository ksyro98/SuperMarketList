package com.example.supermarketlist.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.supermarketlist.R
import kotlinx.android.synthetic.main.activity_add_item.*

const val ADD_ITEM_ACT = 314
const val ITEM_EXTRA_NAME = "item extra name"


class AddItemActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)

        val result = Intent(this, MainActivity::class.java)

        okTextView.setOnClickListener {
            result.putExtra(ITEM_EXTRA_NAME, itemEditText.text.toString())
            setResult(RESULT_OK, result)
            finish()
        }

        cancelTextView.setOnClickListener {
            setResult(RESULT_CANCELED, result)
            finish()
        }
    }

}