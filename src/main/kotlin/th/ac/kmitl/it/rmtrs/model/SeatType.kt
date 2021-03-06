package th.ac.kmitl.it.rmtrs.model

import org.hibernate.annotations.SQLDelete
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@SQLDelete(sql = "UPDATE seat_type SET is_active = false WHERE id = ?")
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
    val seats: MutableSet<Seat> = HashSet()
}