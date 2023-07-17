package com.blibli.composesample.ui.navigation

sealed class Route(val path : String) {
  object HomePage : Route("home_age")
  object DetailPage : Route("detail_page/{movieId}")
}