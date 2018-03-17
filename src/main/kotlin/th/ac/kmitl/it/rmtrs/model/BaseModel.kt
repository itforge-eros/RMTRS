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

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
@JsonIgnoreProperties(
        value = ["createdAt", "updatedAt"],
        allowGetters = true
)
@Where(clause = "is_active=1")
abstract class BaseModel(
        @CreatedDate
        @Column(name = "create_at")
        var createAt: LocalDateTime? = null,
        @LastModifiedDate
        @Column(name = "update_at")
        var updateAt: LocalDateTime? = null,

        @Column(name = "is_active")
        var isActive: Boolean = true
)