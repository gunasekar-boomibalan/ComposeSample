package com.blibli.composesample.ui.movie_detail_view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.blibli.composesample.R
import com.blibli.composesample.ui.home_view.Loader

@OptIn(ExperimentalMaterial3Api::class) @Composable fun MovieDetailPage(
  navController: NavController, movieId: String, viewModel: MovieDetailViewModel = hiltViewModel()
) {
  LaunchedEffect(true) {
    viewModel.searchMovies(movieId)
  }
  Column(modifier = Modifier.fillMaxSize(1f)) {
    TopAppBar(
      title = {
        Text(
          viewModel.movieDetail.Title ?: "",
          maxLines = 1,
          overflow = TextOverflow.Ellipsis,
          color = Color.White
        )
      }, colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Blue)
    )
    Box(
      modifier = Modifier
        .weight(1f)
        .background(Color.White)
    ) {
      if (viewModel.isLoaderVisible) {
        Loader()
      }else{
        MovieDetail(viewModel)
      }
    }
  }
}

@Composable fun MovieDetail(viewModel: MovieDetailViewModel) {
  LazyColumn(
    modifier = Modifier.fillMaxSize(1f).padding(horizontal = 16.dp), horizontalAlignment = Alignment.CenterHorizontally
  ) {
    item {
      AsyncImage(
        modifier = Modifier
          .fillMaxWidth(0.6f)
          .aspectRatio(0.56f),
        model = ImageRequest.Builder(LocalContext.current).data(viewModel.movieDetail.Poster)
          .placeholder(R.mipmap.ic_launcher).crossfade(true).build(),
        contentDescription = "cart_image",
        contentScale = ContentScale.FillBounds,
      )
      Spacer(modifier = Modifier.height(16.dp))
      Text(text = viewModel.movieDetail.Title ?: "", style = MaterialTheme.typography.titleLarge)
      Text(text = viewModel.movieDetail.Genre ?: "", style = MaterialTheme.typography.bodyLarge)
      Text(text = viewModel.movieDetail.Year ?: "", style = MaterialTheme.typography.bodyLarge)
      Spacer(modifier = Modifier.height(16.dp))
      Row(modifier = Modifier.fillMaxWidth(1f)) {
        Row(modifier = Modifier.fillMaxWidth(0.5f)) {
          Text(text = "Director : ", style = MaterialTheme.typography.bodyLarge)
          Text(
            text = viewModel.movieDetail.Director ?: "", style = MaterialTheme.typography.bodyMedium
          )
        }
        Row(modifier = Modifier.fillMaxWidth(1f)) {
          Text(text = "Actor : ", style = MaterialTheme.typography.bodyLarge)
          Text(
            text = viewModel.movieDetail.Actors ?: "", style = MaterialTheme.typography.bodyMedium
          )
        }
      }
      Spacer(modifier = Modifier.height(8.dp))

      Row(modifier = Modifier.fillMaxWidth(1f)) {
        Row(modifier = Modifier.fillMaxWidth(0.5f)) {
          Text(text = "Language : ", style = MaterialTheme.typography.bodyLarge)
          Text(
            text = viewModel.movieDetail.Language ?: "", style = MaterialTheme.typography.bodyMedium
          )
        }

        Row(modifier = Modifier.fillMaxWidth(1f)) {
          Text(text = "Country : ", style = MaterialTheme.typography.bodyLarge)
          Text(
            text = viewModel.movieDetail.Country ?: "", style = MaterialTheme.typography.bodyMedium
          )
        }
      }
      Spacer(modifier = Modifier.height(16.dp))

      Text(
        text = "Plot",
        modifier = Modifier.fillMaxWidth(1f),
        style = MaterialTheme.typography.bodyLarge
      )
      Text(
        text = viewModel.movieDetail.Plot ?: "",
        modifier = Modifier.fillMaxWidth(1f),
        style = MaterialTheme.typography.bodyMedium
      )
      Spacer(modifier = Modifier.height(16.dp))
    }
  }
}