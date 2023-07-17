package com.blibli.composesample.ui.home_view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.blibli.composesample.R
import com.blibli.composesample.data.network.model.Search
import com.blibli.composesample.ui.navigation.Route

@Composable
fun TabOne(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {
  Box(
    modifier = Modifier
      .fillMaxSize(1f)
      .padding(8.dp)
  ) {
    if (viewModel.movieList.Search.isNullOrEmpty()) {
      val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.no_data))
      val progress by animateLottieCompositionAsState(
        composition, isPlaying = true, iterations = LottieConstants.IterateForever, speed = 1.0f
      )
      LottieAnimation(
        composition = composition,
        progress = { progress },
        modifier = Modifier
          .fillMaxWidth(0.8f)
          .align(Alignment.Center)
      )
    } else {
      viewModel.movieList.Search?.let { movie ->
        LazyColumn(modifier = Modifier.fillMaxSize(1f)) {
          items(movie) {
            it?.let {
              ListItem(navController, it)
            }
          }
        }
      }
    }
  }
}

@Composable
fun ListItem(navController: NavController, movie: Search) {
  Card(
    elevation = CardDefaults.cardElevation(
      defaultElevation = 4.dp
    ),
    colors = CardDefaults.cardColors(
      containerColor = Color.White,
    ),
    modifier = Modifier
      .padding(8.dp)
      .fillMaxWidth(1f),
    shape = RoundedCornerShape(8.dp),
  ) {
    Row(modifier = Modifier.clickable {
      navController.navigate(
        Route.DetailPage.path.replace(
          oldValue = "{movieId}",
          newValue = "${movie.imdbID}"
        )
      )
    }) {
      AsyncImage(
        modifier = Modifier
          .fillMaxWidth(0.2f)
          .aspectRatio(0.56f),
        model = ImageRequest.Builder(LocalContext.current)
          .data(movie.Poster)
          .placeholder(R.mipmap.ic_launcher)
          .crossfade(true).build(),
        contentDescription = "cart_image",
        contentScale = ContentScale.FillBounds,
      )
      Column(modifier = Modifier.padding(16.dp)) {
        Text(
          text = movie.Title ?: "",
          style = MaterialTheme.typography.titleLarge,
          color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
          text = movie.Type ?: "",
          style = MaterialTheme.typography.bodyMedium,
          color = Color.Black
        )
      }
    }
  }
}
