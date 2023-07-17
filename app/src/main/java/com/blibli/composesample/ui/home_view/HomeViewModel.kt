package com.blibli.composesample.ui.home_view

import android.annotation.SuppressLint
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blibli.composesample.data.db.History
import com.blibli.composesample.data.network.model.ImdbResponse
import com.blibli.composesample.data.repo.HomeRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@SuppressLint("MutableCollectionMutableState")
@HiltViewModel
class HomeViewModel @Inject constructor(val repo: HomeRepo) : ViewModel() {
  var isLoaderVisible by mutableStateOf(false)
  var searchHistory by mutableStateOf(ArrayList<History>())
  var searchTerm by mutableStateOf("")
  var movieList by mutableStateOf(ImdbResponse())
  var tabIndex by mutableIntStateOf(0)
  val context = Dispatchers.IO + SupervisorJob()

  fun searchMovies() {
    isLoaderVisible = true
    tabIndex = 0
    viewModelScope.launch {
      withContext(context) {
        addHistory()
      }
      withContext(context) {
        flowOf(repo.getMovies(searchTerm)).collect {
          movieList = it
          isLoaderVisible = false
        }
      }
    }
  }

  private fun addHistory() {
    searchHistory.forEach {
      if (it.searchKey == searchTerm) {
        repo.removeHistory(it)
      }
    }
    repo.addHistory(searchTerm)
  }

  fun getHistory() {
    viewModelScope.launch(context) {
      withContext(context) {
        repo.getHistory().flowOn(Dispatchers.IO).catch { }.collectLatest {
          searchHistory = ArrayList(it)
        }
      }
    }
  }
}
