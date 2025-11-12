package gab.streaming.movie_info.infra.config

import jakarta.annotation.PreDestroy
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CoroutineConfig {

    private val supervisorJob = SupervisorJob()

    @Bean
    fun kafkaCoroutineScope(): CoroutineScope = CoroutineScope(Dispatchers.IO + supervisorJob)

    @PreDestroy
    fun shutdown() {
        supervisorJob.cancel()
    }
}