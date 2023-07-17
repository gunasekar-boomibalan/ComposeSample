package com.blibli.composesample.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [History::class], version = 1, exportSchema = false)
abstract class SampleDatabase() : RoomDatabase() {

  abstract fun historyDao(): HistoryDao

  companion object {
    operator fun invoke(context: Context): SampleDatabase {
      return buildDatabase(context)
    }

    private fun buildDatabase(context: Context): SampleDatabase {
      val dbname = "SampleDatabase.db"

      return Room.databaseBuilder(
        context, SampleDatabase::class.java, dbname
      ).fallbackToDestructiveMigration()
        .build()
    }
  }
}