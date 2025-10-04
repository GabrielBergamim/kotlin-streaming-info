package gab.streaming.movie_info.application.service

import gab.streaming.movie_info.application.port.`in`.UpdateMovieInfo
import gab.streaming.movie_info.application.port.out.MovieRepository
import gab.streaming.movie_info.application.port.out.SearchMovieInfo
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class MovieService(
    val searchMovieInfo: SearchMovieInfo,
    val movieRepository: MovieRepository
): UpdateMovieInfo {

    override fun updateMovieInfo(id: UUID) {
        movieRepository.findMovieById(id).ifPresent { movie ->
            val movieInfo = searchMovieInfo.searchMovieInfo(movie.title)

            movieInfo?.let {
                movie.updateInfo(it)
                movieRepository.save(movie)
            }
        }
    }
}