package com.blibli.composesample.data.network

import com.blibli.composesample.data.network.model.ImdbResponse
import com.blibli.composesample.data.network.model.MovieDetailsResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class SampleApi(private val client: HttpClient) {
  suspend fun getMovies(searchTerm: String): ImdbResponse =
    client.get("${HttpRoutes.getMoviesList}$searchTerm")

  suspend fun getMovieDetails(id: String): MovieDetailsResponse =
    client.get("${HttpRoutes.getMovieDetails}$id")
}