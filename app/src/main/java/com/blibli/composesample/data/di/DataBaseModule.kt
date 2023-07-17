package com.blibli.composesample.data.di

import android.content.Context
import com.blibli.composesample.data.db.SampleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) class DataBaseModule {
  @Provides @Singleton fun providesDataBase(@ApplicationContext context: Context): SampleDatabase =
    SampleDatabase.invoke(context)
}