package gab.streaming.movie_info.infra.config

import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Configuration

@Configuration
@EnableFeignClients(basePackages = ["gab.streaming.movie_info"])
class ImdbConfig {
}