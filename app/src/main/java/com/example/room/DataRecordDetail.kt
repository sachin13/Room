package com.example.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.room.data.DataRecord
import com.example.room.viewmodels.DataRecordViewModel
import kotlinx.android.synthetic.main.activity_data_record_detail.*

class DataRecordDetail : AppCompatActivity() {

    private lateinit var datarecordViewModel: DataRecordViewModel
    private var recordId: Long = 0L
    private var isEdit: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_data_record_detail)
        datarecordViewModel = ViewModelProvider(this).get(DataRecordViewModel::class.java)

        if (intent.hasExtra(Constants.DATA_RECORD_ID)) {
            recordId = intent.getLongExtra(Constants.DATA_RECORD_ID, 0L)
            datarecordViewModel.get(recordId).observe(this, Observer {
                val viewId = findViewById<TextView>(R.id.datarecord_id)
                val viewRecord = findViewById<EditText>(R.id.datarecord_record)
                if (it != null) {
                    viewId.text = it.id.toString()
                    viewRecord.setText(it.record)
                }
            })
            isEdit = true
        }

        val btnSave = btnSave
        btnSave.setOnClickListener { view ->
            val id = 0L
            val record = datarecord_record.text.toString()
            val item = DataRecord(id = id, record = record)
            datarecordViewModel.insert(item)
            finish()
        }

        val btnUpdate = btnUpdate
        btnUpdate.setOnClickListener { view ->
            val id = datarecord_id.text.toString().toLong()
            val record = datarecord_record.text.toString()
            val item = DataRecord(id = id, record = record)
            datarecordViewModel.update(item)
            finish()
        }

        val btnDelete = btnDelete
        btnDelete.setOnClickListener {
            val id = datarecord_id.text.toString().toLong()
            val record = datarecord_record.text.toString()

            val item = DataRecord(id = id, record = record)
            datarecordViewModel.delete(item)
            finish()
        }

        if (isEdit) {
            btnSave.visibility = View.GONE
        } else {
            btnUpdate.visibility = View.GONE
            btnDelete.visibility = View.GONE
        }
    }
}
