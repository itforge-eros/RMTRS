package th.ac.kmitl.it.rmtrs.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.hibernate.annotations.Where
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@Where(clause = "is_active = true")
@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
@JsonIgnoreProperties(
        value = ["createdAt", "updatedAt"],
        allowGetters = true
)
abstract class BaseModel(
        @CreatedDate
        @Column(name = "create_at")
        var createAt: LocalDateTime = LocalDateTime.now(),
        @LastModifiedDate
        @Column(name = "update_at")
        var updateAt: LocalDateTime = LocalDateTime.now(),

        @Column(name = "is_active")
        var isActive: Boolean = true
)