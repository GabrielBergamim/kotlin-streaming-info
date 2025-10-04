package gab.streaming.movie_info.application.port.out

import gab.streaming.movie_info.domain.model.Movie
import java.util.Optional
import java.util.UUID

interface MovieRepository {

    fun findMovieById(id: UUID): Optional<Movie>
    fun save(movie: Movie)
}