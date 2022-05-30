package com.example.room.data

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface DataRecordDao {

    @Query("SELECT * from datarecords")
    fun getall(): LiveData<List<DataRecord>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
     fun insert(item: DataRecord)

    @Query("SELECT * FROM datarecords WHERE datarecords.id == :id")
    fun get(id: Long): LiveData<DataRecord>

    @Update
     fun update(vararg items: DataRecord)

    @Delete
     fun delete(vararg items: DataRecord)
}