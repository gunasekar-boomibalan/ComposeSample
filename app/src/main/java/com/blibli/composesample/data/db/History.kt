package com.blibli.composesample.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "History")
data class History(
  @PrimaryKey(autoGenerate = true) var id: Int = 0,
  var searchKey: String
)