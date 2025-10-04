package gab.streaming.movie_info.infra.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class SearchApiResponseDto(
    val ok: Boolean,

    val description: List<MovieDescriptionDto>,

    val errorCode: Int
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class DetailsApiResponseDto(
    val ok: Boolean,

    val short: MovieDetailDto
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class MovieDescriptionDto(
    @field:JsonProperty("#AKA")
    val title: String,

    @field:JsonProperty("#IMDB_ID")
    val imdbId: String,
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class MovieDetailDto(
    val name: String,
    val image: String,
    val description: String,
    val aggregateRating: AggregateRatingDto,
    val genre: List<String>,
    val datePublished: String,
    val director: List<DirectorDto>,
    val duration: String
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class AggregateRatingDto(
    val ratingValue: Double
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class DirectorDto(
    val name: String
)