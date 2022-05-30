package com.example.room

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.room.adapters.DataRecordAdapter
import com.example.room.viewmodels.DataRecordViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.data_record_list_recyclerview.*

class MainActivity : AppCompatActivity() {

    private lateinit var datarecordViewModel: DataRecordViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        fab_add.setOnClickListener { _ ->
            val intent = Intent(this, DataRecordDetail::class.java)
            startActivity(intent)
        }
        val recyclerView = datarecord_list
        val adapter = DataRecordAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        datarecordViewModel = ViewModelProvider(this).get(DataRecordViewModel::class.java)
        datarecordViewModel.allItems.observe(this, Observer { items ->
            items?.let { adapter.setItems(it) }
        })
    }
}