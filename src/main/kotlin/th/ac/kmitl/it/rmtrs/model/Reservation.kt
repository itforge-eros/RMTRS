package th.ac.kmitl.it.rmtrs.model

import org.hibernate.annotations.SQLDelete
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

        var checkedIntime: LocalDateTime? = null,

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
    val tickets: MutableSet<Ticket> = HashSet()
}