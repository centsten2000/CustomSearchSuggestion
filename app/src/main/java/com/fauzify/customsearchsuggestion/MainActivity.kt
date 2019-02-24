package com.fauzify.customsearchsuggestion

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.AutoCompleteTextView
import android.R



class MainActivity : AppCompatActivity() {

    private lateinit var itemList: MutableList<ExampleItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addIntemList()

        val editText = findViewById(R.id.actv_search)
        val adapter = ExampleItemAdapter(this, itemList)
        editText.setAdapter(adapter)
    }

    fun addIntemList(){
        itemList.add(ExampleItem("Item A",R.drawable.ic_a))
        itemList.add(ExampleItem("Item B",R.drawable.ic_b))
        itemList.add(ExampleItem("Item C",R.drawable.ic_c))
        itemList.add(ExampleItem("Item D",R.drawable.ic_d))
        itemList.add(ExampleItem("Item AB",R.drawable.ic_a))
        itemList.add(ExampleItem("Item BC",R.drawable.ic_c))
        itemList.add(ExampleItem("Item CD",R.drawable.ic_d))
        itemList.add(ExampleItem("Item AC",R.drawable.ic_a))
        itemList.add(ExampleItem("Item BD",R.drawable.ic_b))
    }
}
