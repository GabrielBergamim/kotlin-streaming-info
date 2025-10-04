package gab.streaming.movie_info

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MovieInfoApplication

fun main(args: Array<String>) {
	runApplication<MovieInfoApplication>(*args)
}