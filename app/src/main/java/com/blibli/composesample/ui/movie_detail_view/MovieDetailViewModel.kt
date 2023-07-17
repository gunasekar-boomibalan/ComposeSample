package com.blibli.composesample.ui.movie_detail_view

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blibli.composesample.data.network.model.MovieDetailsResponse
import com.blibli.composesample.data.repo.MovieDetailRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(val repo: MovieDetailRepo) : ViewModel() {
  var movieDetail by mutableStateOf(MovieDetailsResponse())
  var isLoaderVisible by mutableStateOf(false)

  fun searchMovies(movieId: String) {
    isLoaderVisible = true
    viewModelScope.launch(Dispatchers.IO) {
      withContext(SupervisorJob()) {
        flowOf(repo.getMoviesDetail(movieId)).collect {
          movieDetail = it
          isLoaderVisible = false
        }
      }
    }
  }
}