package com.blibli.composesample.ui.home_view

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.blibli.composesample.R
import com.blibli.composesample.ui.theme.DialogBackGround

@Composable @OptIn(ExperimentalMaterial3Api::class) fun HomeView(
  navController: NavController,
  viewModel: HomeViewModel = hiltViewModel()
) {
  val context = LocalContext.current
  Column(Modifier.fillMaxSize(1f)) {
    TopAppBar(title = {
      Text(
        "Simple App", maxLines = 1, overflow = TextOverflow.Ellipsis, color = Color.White
      )
    },
      colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Blue),
      navigationIcon = {
        IconButton(onClick = {
          Toast.makeText(context, "Menu Icon Clicked", Toast.LENGTH_SHORT).show()
        }) {
          Icon(Icons.Filled.Menu, contentDescription = null, tint = Color.White)
        }
      },
      actions = {
        IconButton(onClick = {
          Toast.makeText(context, "Favourite Button Clicked", Toast.LENGTH_SHORT).show()
        }) {
          Icon(
            Icons.Filled.Favorite,
            contentDescription = "Localized description",
            tint = Color.White
          )
        }
      })
    Box(modifier = Modifier.weight(1f))
    {
      if (viewModel.isLoaderVisible)
        Loader()
      else
        TabScreen(navController, viewModel)
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable fun TabScreen(navController: NavController, viewModel: HomeViewModel) {
  val tabs = listOf("Home", "History")
  val keyboardController = LocalSoftwareKeyboardController.current
  val focusRequester = remember { FocusRequester() }
  val focusManager = LocalFocusManager.current

  Column(modifier = Modifier.fillMaxWidth()) {
    TextField(
      value = viewModel.searchTerm,
      maxLines = 1,
      singleLine = true,
      keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
      keyboardActions = KeyboardActions(onDone = {
        focusManager.clearFocus()
        keyboardController?.hide()
        viewModel.searchMovies()
      }),
      onValueChange = {
        viewModel.searchTerm = it
      },
      label = {
        Text("Movie Name")
      },
      placeholder = {
        Text("Enter the Movie Name")
      },
      modifier = Modifier
        .padding(horizontal = 16.dp)
        .padding(top = 16.dp)
        .fillMaxWidth(1f)
        .focusRequester(focusRequester)
    )
    Column(modifier = Modifier.weight(1f)) {
      when (viewModel.tabIndex) {
        0 -> TabOne(navController, viewModel)
        1 -> TabTwo(viewModel)
      }
    }
    val indicator = @Composable { tabPositions: List<TabPosition> ->
      TopIndicator(Color.Blue, Modifier.tabIndicatorOffset(tabPositions[viewModel.tabIndex]))
    }
    TabRow(selectedTabIndex = viewModel.tabIndex, indicator = indicator) {
      tabs.forEachIndexed { index, title ->
        Tab(
          text = { Text(title) },
          selected = viewModel.tabIndex == index,
          onClick = { viewModel.tabIndex = index })
      }
    }
  }
}

@Composable fun TopIndicator(color: Color, modifier: Modifier = Modifier) {
  Box(
    modifier
      .fillMaxWidth()
      .offset(y = (-46).dp)
      .height(2.dp)
      .background(color = color)
  )
}

@Composable
fun Loader() {
  Box(modifier = Modifier
    .fillMaxSize(1f)
    .clickable { }
    .background(DialogBackGround)) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loader))
    val progress by animateLottieCompositionAsState(
      composition, isPlaying = true, iterations = LottieConstants.IterateForever, speed = 1.0f
    )
    LottieAnimation(
      composition = composition,
      progress = { progress },
      modifier = Modifier
        .width(100.dp)
        .align(Alignment.Center)
    )
  }
}