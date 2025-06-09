package com.example.time.data.db.timescreen.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.time.data.db.timescreen.entity.TimeDataEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TimeDataDao {

    // All time zone
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTimeData(data: List<TimeDataEntity>)

    @Query("SELECT * FROM time_data")
    fun getAllTimeData(): Flow<List<TimeDataEntity>>

    @Query("SELECT * FROM time_data WHERE timeZone = :timeZone")
    fun getTimeDataByTimeZone(timeZone: String): Flow<List<TimeDataEntity>>

    @Query("DELETE FROM time_data WHERE timeZone = :timeZone")
    suspend fun deleteTimeDataById(timeZone: String)

    // Selected time zone
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSelectedTimeData(data: TimeDataEntity)

    @Query("SELECT * FROM time_data WHERE isSelected = 1")
    fun getSelectedTimeData(): Flow<List<TimeDataEntity>>

    @Query("DELETE FROM time_data WHERE timezone = :timezone AND isSelected = 1")
    suspend fun deleteSelectedTimezone(timezone: String)

    // Update
    @Update
    suspend fun updateTimeData(timeData: TimeDataEntity)

}