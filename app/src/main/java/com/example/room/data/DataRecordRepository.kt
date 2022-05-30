package com.example.room.data

import androidx.lifecycle.LiveData

class DataRecordRepository(private val datarecordDao: DataRecordDao) {

    val allItems: LiveData<List<DataRecord>> = datarecordDao.getall()

    fun get(id: Long): LiveData<DataRecord> {
        return datarecordDao.get(id)
    }

     fun update(item: DataRecord) {
        datarecordDao.update(item)
    }

     fun insert(item: DataRecord) {
        datarecordDao.insert(item)
    }

     fun delete(item: DataRecord) {
        datarecordDao.delete(item)
    }
}