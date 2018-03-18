package th.ac.kmitl.it.rmtrs.model

import org.hibernate.annotations.SQLDelete
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@SQLDelete(sql = "UPDATE seat SET is_active = false WHERE id = ?")
data class Seat(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0,

        @NotNull
        var row: String = "DEFAULT",

        @NotNull
        var number: Int = 0
): BaseModel() {

    @ManyToOne
    @JoinColumn(nullable = false)
    lateinit var seatType: SeatType

    @ManyToOne
    @JoinColumn(nullable = false)
    lateinit var  theatre: Theatre

    @OneToMany(
            mappedBy = "seat",
            fetch = FetchType.LAZY,
            cascade = [CascadeType.ALL],
            orphanRemoval = false
    )
    val tickets: MutableSet<Ticket> = HashSet()
}