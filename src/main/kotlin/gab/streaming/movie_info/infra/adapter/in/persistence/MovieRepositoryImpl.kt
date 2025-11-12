package gab.streaming.movie_info.infra.adapter.`in`.persistence

import gab.streaming.movie_info.application.port.out.MovieRepository
import gab.streaming.movie_info.domain.model.Movie
import gab.streaming.movie_info.infra.adapter.`in`.persistence.jpa.MovieRepositoryJpa
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import java.util.Optional
import java.util.UUID

@Repository
class MovieRepositoryImpl(val repositoryJpa: MovieRepositoryJpa): MovieRepository {

    override fun findMovieByIdOrNull(id: UUID): Movie? = repositoryJpa.findByIdOrNull(id)

    override fun save(movie: Movie) {
        repositoryJpa.save(movie)
    }
}