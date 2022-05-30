package com.example.room.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.room.Constants
import com.example.room.DataRecordDetail
import com.example.room.R
import com.example.room.data.DataRecord


class DataRecordAdapter internal constructor(context: Context) :

    RecyclerView.Adapter<DataRecordAdapter.DataRecordViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var itemsList = emptyList<DataRecord>().toMutableList()

    private val onClickListener: View.OnClickListener

    init {
        onClickListener = View.OnClickListener { v ->
            val item = v.tag as DataRecord
            val intent = Intent(v.context, DataRecordDetail::class.java).apply {
                putExtra(Constants.DATA_RECORD_ID, item.id)
            }
            v.context.startActivity(intent)
        }
    }

    inner class DataRecordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemId: TextView = itemView.findViewById(R.id.datarecord_viewholder_id)
        val itemRecord: TextView = itemView.findViewById(R.id.datarecord_viewholder_record)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataRecordViewHolder {
        val itemView = inflater.inflate(R.layout.datarecord_viewholder, parent, false)
        return DataRecordViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DataRecordViewHolder, position: Int) {
        val current = itemsList[position]
        holder.itemView.tag = current
        with(holder) {
            itemId.text = current.id.toString()
            itemRecord.text = current.record
            itemView.setOnClickListener(onClickListener)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    internal fun setItems(items: List<DataRecord>) {
        this.itemsList = items.toMutableList()
        notifyDataSetChanged()
    }

    override fun getItemCount() = itemsList.size
}