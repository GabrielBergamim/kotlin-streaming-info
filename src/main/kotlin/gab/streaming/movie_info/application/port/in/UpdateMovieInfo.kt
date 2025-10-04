package gab.streaming.movie_info.application.port.`in`

import java.util.UUID

interface UpdateMovieInfo {

    fun updateMovieInfo(id: UUID)
}