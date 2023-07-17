package com.blibli.composesample.data.di

import android.util.Log
import com.blibli.composesample.data.network.SampleApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.DefaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.features.observer.ResponseObserver
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module @InstallIn(SingletonComponent::class)
class ApiModule {
  companion object {
    private const val TIME_OUT = 60_000
  }

  @Provides
  @Singleton
  fun provideApiClient(): HttpClient = HttpClient(Android) {
    install(JsonFeature) {
      serializer = KotlinxSerializer(Json  {
        prettyPrint = true
        isLenient = true
        ignoreUnknownKeys = true
      })

      engine {
        connectTimeout = TIME_OUT
        socketTimeout = TIME_OUT
      }
    }

    install(Logging) {
      logger = object : Logger {
        override fun log(message: String) {
          Log.v("Logger Ktor =>", message)
        }
      }
      level = LogLevel.ALL
    }

    install(ResponseObserver) {
      onResponse { response ->
        Log.d("HTTP status:", "${response.status.value}")
      }
    }

    install(DefaultRequest) {
      header(HttpHeaders.ContentType, ContentType.Application.Json)
    }
  }

  @Provides
  @Singleton
  fun provideSampleApi(client: HttpClient): SampleApi = SampleApi(client)
}