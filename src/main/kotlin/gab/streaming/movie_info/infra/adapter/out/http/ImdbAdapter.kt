package gab.streaming.movie_info.infra.adapter.out.http

import gab.streaming.movie_info.application.port.out.SearchMovieInfo
import gab.streaming.movie_info.domain.model.MovieInfo
import gab.streaming.movie_info.infra.client.ImdbFeignClient
import gab.streaming.movie_info.infra.dto.MovieDescriptionDto
import io.github.resilience4j.bulkhead.annotation.Bulkhead
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import io.github.resilience4j.retry.annotation.Retry
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.Duration

@Component
class ImdbAdapter(val client: ImdbFeignClient) : SearchMovieInfo {

    @CircuitBreaker(name = "imdbFeignClient", fallbackMethod = "onFail")
    @Retry(name = "imdbFeignClient")
    @Bulkhead(name = "imdbFeignClient", type = Bulkhead.Type.SEMAPHORE)
    override fun searchMovieInfo(title: String): MovieInfo? {
        val response = client.searchMovies(title)

        val movieDescription = findMovieInfo(response.description, title)

        movieDescription?.let {
            try {
                val movie = client.searchMovie(it.imdbId);
                return MovieInfo(
                    description = movie.short.description,
                    genre = movie.short.genre.joinToString(", "),
                    director = movie.short.director.joinToString(", ") { director -> director.name },
                    posterUrl = movie.short.image,
                    rating = movie.short.aggregateRating.ratingValue,
                    datePublished = LocalDate.parse(movie.short.datePublished),
                    durationMinutes = Duration.parse(movie.short.duration)
                )
            } catch (ex: Exception) {
                ex.printStackTrace()
                return null
            }
        }

        return null
    }

    private fun findMovieInfo(movies: List<MovieDescriptionDto>, name: String): MovieDescriptionDto? {
        return movies.find { it.title.trim().equals(name.trim(), ignoreCase = true) }
    }

    fun onFail(title: String, ex: Exception): MovieInfo? {
        println("fail to search: $title")
        ex.printStackTrace()
        throw ex;
    }
}
