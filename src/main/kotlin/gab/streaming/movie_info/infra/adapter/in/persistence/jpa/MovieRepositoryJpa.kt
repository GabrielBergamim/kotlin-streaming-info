package gab.streaming.movie_info.infra.adapter.`in`.persistence.jpa

import gab.streaming.movie_info.domain.model.Movie
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface MovieRepositoryJpa: JpaRepository<Movie, UUID>  {
}