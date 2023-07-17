package com.blibli.composesample.ui.home_view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavController

@Composable
fun TabTwo(viewModel: HomeViewModel) {

  val lifecycle = LocalLifecycleOwner.current.lifecycle
  DisposableEffect(lifecycle) {
    val observer = LifecycleEventObserver { _, event ->
      when (event) {
        Lifecycle.Event.ON_RESUME -> {
          viewModel.getHistory()
        }

        else -> {}
      }
    }
    lifecycle.addObserver(observer)
    onDispose {
      lifecycle.removeObserver(observer)
    }
  }

  LazyColumn(modifier = Modifier.padding(top = 16.dp)) {
    items(viewModel.searchHistory) {
      Card(
        modifier = Modifier
          .padding(horizontal = 16.dp, vertical = 4.dp)
          .fillMaxWidth(1f)
          .clickable {
            viewModel.searchTerm = it.searchKey
            viewModel.searchMovies()
          },
        elevation = CardDefaults.cardElevation(
          defaultElevation = 4.dp
        ),
        colors = CardDefaults.cardColors(
          containerColor = Color.White,
        )
      ) {
        Text(
          text = it.searchKey,
          modifier = Modifier
            .padding(16.dp)
        )
      }
    }
  }
}