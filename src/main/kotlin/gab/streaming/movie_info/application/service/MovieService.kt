package gab.streaming.movie_info.application.service

import gab.streaming.movie_info.application.port.`in`.UpdateMovieInfo
import gab.streaming.movie_info.application.port.out.MovieRepository
import gab.streaming.movie_info.application.port.out.SearchMovieInfo
import gab.streaming.movie_info.domain.model.Movie
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class MovieService(
    private val searchMovieInfo: SearchMovieInfo,
    private val movieRepository: MovieRepository
): UpdateMovieInfo {

    private val logger = LoggerFactory.getLogger(MovieService::class.java)

    override fun updateMovieInfo(id: UUID) {
        findById(id)?.let { movie ->
            logger.info("Updating movie info for movie with id $id and title ${movie.title}")
            val movieInfo = searchMovieInfo.searchMovieInfo(movie.title).also {
                if (it == null) {
                    logger.info("Movie info for title ${movie.title} not found from external source")
                }
            }

            movieInfo?.let {
                movie.updateInfo(it)
                movieRepository.save(movie)
            }
        }
    }

    fun findById(id: UUID): Movie? = movieRepository.findMovieByIdOrNull(id).also {
       if (it == null) {
           logger.info("Movie with id $id not found in database")
       }
    }
}