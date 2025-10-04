package gab.streaming.movie_info.infra.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.listener.CommonLoggingErrorHandler
import org.springframework.kafka.listener.ContainerProperties

@EnableKafka
@Configuration
class KafkaConfig {

}