package th.ac.kmitl.it.rmtrs.model

import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
data class SeatType(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0,

        @NotNull
        var name: String = "default",

        @NotNull
        var price: Double = 0.1,

        @NotNull
        @Lob
        var description: String = "default"
): BaseModel() {

    @OneToMany(
            mappedBy = "seatType",
            cascade = [CascadeType.ALL],
            fetch = FetchType.LAZY,
            orphanRemoval = false
    )
    @Where(clause = "is_active = true")
    val seats: MutableSet<Seat> = HashSet()
}