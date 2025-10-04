package gab.streaming.movie_info.domain.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes
import java.time.Duration
import java.time.LocalDate
import java.util.UUID

@Entity
@Table(name = "movies")
class Movie(
    @Id
    val id: UUID,

    @Column(name = "title", nullable = false)
    val title: String,

    @Column(name = "description", columnDefinition = "TEXT")
    var description: String?,

    @Column(name = "genre")
    var genre: String?,

    @Column(name = "director")
    var director: String?,

    @Column(name = "poster_url")
    var posterUrl: String?,

    @Column(name = "rating")
    var rating: Double?,

    @Column(name = "duration", columnDefinition = "interval")
    @JdbcTypeCode(SqlTypes.INTERVAL_SECOND)
    var duration: Duration?,

    @Column(name = "release_date")
    var releaseDate: LocalDate?
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Movie) return false
        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    fun updateInfo(info: MovieInfo) {
        this.description = info.description
        this.genre = info.genre
        this.director = info.director
        this.posterUrl = info.posterUrl
        this.rating = info.rating
        this.releaseDate = info.datePublished
        this.duration = info.durationMinutes
    }
}

data class MovieInfo(
    val posterUrl: String,
    val description: String,
    val rating: Double,
    val genre: String,
    val director: String,
    val datePublished: LocalDate,
    val durationMinutes: Duration,
)