package com.example.time.data.db.stopwatchscreen.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.time.data.db.stopwatchscreen.entity.StopwatchEntity

@Dao
interface StopwatchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStopwatch(stopwatch: StopwatchEntity)

    @Query("SELECT * FROM stopwatch")
    suspend fun getAllStopwatches(): List<StopwatchEntity>

    @Query("DELETE FROM stopwatch")
    suspend fun resetStopwatches()
}