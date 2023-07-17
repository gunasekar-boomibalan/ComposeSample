package com.blibli.composesample.data.di

import com.blibli.composesample.data.db.SampleDatabase
import com.blibli.composesample.data.network.SampleApi
import com.blibli.composesample.data.repo.HomeRepo
import com.blibli.composesample.data.repo.MovieDetailRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module @InstallIn(ViewModelComponent::class)
class RepoModel {

  @Provides
  fun provideHomeRepo(sampleApi: SampleApi, db: SampleDatabase) = HomeRepo(sampleApi, db)

  @Provides
  fun provideMovieDetailRepo(sampleApi: SampleApi) = MovieDetailRepo(sampleApi)
}