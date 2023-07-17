package com.blibli.composesample.data.repo

import com.blibli.composesample.data.network.SampleApi
import javax.inject.Inject

class MovieDetailRepo @Inject constructor(val api: SampleApi) {
  suspend fun getMoviesDetail(id: String) = api.getMovieDetails(id)
}