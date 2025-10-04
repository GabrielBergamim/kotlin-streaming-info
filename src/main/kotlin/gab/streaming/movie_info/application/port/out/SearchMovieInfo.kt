package gab.streaming.movie_info.application.port.out

import gab.streaming.movie_info.domain.model.MovieInfo

interface SearchMovieInfo {

    fun searchMovieInfo(title: String): MovieInfo?
}