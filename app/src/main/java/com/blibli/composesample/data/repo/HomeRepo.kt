package com.blibli.composesample.data.repo

import com.blibli.composesample.data.db.History
import com.blibli.composesample.data.db.SampleDatabase
import com.blibli.composesample.data.network.SampleApi
import javax.inject.Inject

class HomeRepo @Inject constructor(val api: SampleApi, val db: SampleDatabase) {

  suspend fun getMovies(searchTerm: String) = api.getMovies(searchTerm)

  fun getHistory() = db.historyDao().getHistory()

  fun addHistory(historyKey: String) =
    db.historyDao().addHistory(History(searchKey = historyKey))

  fun removeHistory(history : History) {
    db.historyDao().removeHistory(history)
  }
}