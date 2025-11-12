package gab.streaming.movie_info.infra.adapter.out.message

import gab.streaming.movie_info.application.port.`in`.UpdateMovieInfo
import gab.streaming.movie_info.infra.dto.MovieMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.slf4j.LoggerFactory.getLogger
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.annotation.RetryableTopic
import org.springframework.kafka.support.Acknowledgment
import org.springframework.retry.annotation.Backoff
import org.springframework.stereotype.Component

@Component
class MovieInfoConsumer(
    private val updateMovieInfo: UpdateMovieInfo,
    private val coroutineScope: CoroutineScope) {

    private val logger = getLogger(MovieInfoConsumer::class.java)

    @KafkaListener(topics = ["movie-info-topic"], groupId = "movie-info-group")
    @RetryableTopic(
        backoff = Backoff(delay = 5_000, multiplier = 6.0, maxDelay = 30_000),
        autoCreateTopics = "true",
        dltTopicSuffix = "-dlt",
    )
    fun consume(message: MovieMessage, acknowledgment: Acknowledgment) {
        coroutineScope.launch {
            logger.info("Consumed message: $message")
            updateMovieInfo.updateMovieInfo(message.id)
            acknowledgment.acknowledge()
        }
    }
}