package gab.streaming.movie_info.infra.adapter.`in`.persistence

import gab.streaming.movie_info.application.port.out.MovieRepository
import gab.streaming.movie_info.domain.model.Movie
import gab.streaming.movie_info.infra.adapter.`in`.persistence.jpa.MovieRepositoryJpa
import org.springframework.stereotype.Repository
import java.util.Optional
import java.util.UUID

@Repository
class MovieRepositoryImpl(val repositoryJpa: MovieRepositoryJpa): MovieRepository {

    override fun findMovieById(id: UUID): Optional<Movie> {
        return repositoryJpa.findById(id)
    }

    override fun save(movie: Movie) {
        repositoryJpa.save(movie)
    }
}