package gab.streaming.movie_info.infra.client

import gab.streaming.movie_info.infra.dto.DetailsApiResponseDto
import gab.streaming.movie_info.infra.dto.SearchApiResponseDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "imdbFeignClient", url = "\${imdb.api.url}")
interface ImdbFeignClient {

    @GetMapping("/search")
    fun searchMovies(@RequestParam("q") q: String): SearchApiResponseDto

    @GetMapping("/search")
    fun searchMovie(@RequestParam("tt") tt: String): DetailsApiResponseDto
}