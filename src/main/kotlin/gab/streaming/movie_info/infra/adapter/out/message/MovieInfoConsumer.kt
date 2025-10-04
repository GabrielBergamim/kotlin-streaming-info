package gab.streaming.movie_info.infra.adapter.out.message

import gab.streaming.movie_info.application.port.`in`.UpdateMovieInfo
import gab.streaming.movie_info.infra.dto.MovieMessage
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.annotation.RetryableTopic
import org.springframework.kafka.support.Acknowledgment
import org.springframework.retry.annotation.Backoff
import org.springframework.stereotype.Component

@Component
class MovieInfoConsumer(val updateMovieInfo: UpdateMovieInfo) {

    @KafkaListener(topics = ["movie-info-topic"], groupId = "movie-info-group")
    @RetryableTopic(
        backoff = Backoff(delay = 5_000, multiplier = 6.0, maxDelay = 30_000),
        autoCreateTopics = "true",
        dltTopicSuffix = "-dlt",
    )
    fun consume(message: MovieMessage, acknowledgment: Acknowledgment) {
        println("Consumed message: $message")
        updateMovieInfo.updateMovieInfo(message.id)
        acknowledgment.acknowledge()
    }
}