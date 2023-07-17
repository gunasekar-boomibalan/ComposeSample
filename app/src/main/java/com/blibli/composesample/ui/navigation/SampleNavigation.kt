package com.blibli.composesample.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.blibli.composesample.ui.home_view.HomeView
import com.blibli.composesample.ui.movie_detail_view.MovieDetailPage

@Composable
fun SampleNavigation() {
  val navController = rememberNavController()

  NavHost(navController = navController, startDestination = Route.HomePage.path) {
    composable(route = Route.HomePage.path) {
      HomeView(navController = navController)
    }
    composable(route = Route.DetailPage.path) {
      val movieId = it.arguments?.getString("movieId", "") ?: ""
      MovieDetailPage(
        navController = navController,
        movieId = movieId
      )
    }
  }
}