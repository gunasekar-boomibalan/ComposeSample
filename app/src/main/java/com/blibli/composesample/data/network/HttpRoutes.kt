package com.blibli.composesample.data.network

object HttpRoutes {
  private const val SAMPLE_BASE_URL = "https://www.omdbapi.com/?apikey=9cfa95f8&"
  const val getMoviesList = "${SAMPLE_BASE_URL}s="
  const val getMovieDetails = "${SAMPLE_BASE_URL}i="
}