package com.blibli.composesample.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {
  @Query("SELECT * FROM History ORDER BY id DESC")
  fun getHistory(): Flow<List<History>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun addHistory(history : History): Long

  @Delete(entity = History::class)
  fun removeHistory(history: History)
}