package th.ac.kmitl.it.rmtrs.model

import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@SQLDelete(sql = "UPDATE reservation SET is_active = false WHERE id = ?")
data class Reservation(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0,

        @NotNull
        var isCheckedIn: Boolean = false,

        var checkedInTime: LocalDateTime? = null,

        @NotNull
        var reservedTime: LocalDateTime? = null
): BaseModel() {

    @ManyToOne
    @JoinColumn(nullable = false)
    lateinit var screening: Screening

    @OneToMany(
            mappedBy = "reservation",
            fetch = FetchType.EAGER,
            cascade = [CascadeType.ALL],
            orphanRemoval = false
    )
    @Where(clause = "is_active = true")
    val tickets: MutableSet<Ticket> = HashSet()
}