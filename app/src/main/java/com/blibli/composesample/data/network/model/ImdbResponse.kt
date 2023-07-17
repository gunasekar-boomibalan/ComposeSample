package com.blibli.composesample.data.network.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ImdbResponse(
  @SerializedName("Response") var Response: String? = null,
  @SerializedName("Search") var Search: List<Search?>? = ArrayList(),
  @SerializedName("totalResults") var totalResults: String? = null
)

@Serializable
data class Search(
  @SerializedName("imdbID") var imdbID: String? = null,
  @SerializedName("Poster") var Poster: String? = null,
  @SerializedName("Title") var Title: String? = null,
  @SerializedName("Type") var Type: String? = null,
  @SerializedName("Year") var Year: String? = null
)