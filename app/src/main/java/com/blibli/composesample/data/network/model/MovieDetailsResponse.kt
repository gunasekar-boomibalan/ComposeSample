package com.blibli.composesample.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailsResponse(
  @SerialName("Actors")
  var Actors: String? = null,
  @SerialName("Awards")
  var Awards: String? = null,
  @SerialName("BoxOffice")
  var BoxOffice: String? = null,
  @SerialName("Country")
  var Country: String? = null,
  @SerialName("DVD")
  var DVD: String? = null,
  @SerialName("Director")
  var Director: String? = null,
  @SerialName("Genre")
  var Genre: String? = null,
  @SerialName("imdbID")
  var imdbID: String? = null,
  @SerialName("imdbRating")
  var imdbRating: String? = null,
  @SerialName("imdbVotes")
  var imdbVotes: String? = null,
  @SerialName("Language")
  var Language: String? = null,
  @SerialName("Metascore")
  var Metascore: String? = null,
  @SerialName("Plot")
  var Plot: String? = null,
  @SerialName("Poster")
  var Poster: String? = null,
  @SerialName("Production")
  var Production: String? = null,
  @SerialName("Rated")
  var Rated: String? = null,
  @SerialName("Ratings")
  var Ratings: List<Rating?>? = ArrayList(),
  @SerialName("Released")
  var Released: String? = null,
  @SerialName("Response")
  var Response: String? = null,
  @SerialName("Runtime")
  var Runtime: String? = null,
  @SerialName("Title")
  var Title: String? = null,
  @SerialName("Type")
  var Type: String? = null,
  @SerialName("Website")
  var Website: String? = null,
  @SerialName("Writer")
  var Writer: String? = null,
  @SerialName("Year")
  var Year: String? = null
)

@Serializable
data class Rating(
  @SerialName("Source")
  var Source: String? = null,
  @SerialName("Value")
  var Value: String? = null
)