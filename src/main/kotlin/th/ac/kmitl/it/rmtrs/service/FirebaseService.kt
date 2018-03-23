package th.ac.kmitl.it.rmtrs.service

import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import org.springframework.stereotype.Service
import th.ac.kmitl.it.rmtrs.util.JSON

@Service
class FirebaseService(val ref: DatabaseReference) {

    fun createAvailableSeats(screeningId: Long, seatIds: List<Long>) {
        val seatsUpdate = seatIds.map { it.toString() to "available" }.toMap()
        ref.child("$screeningId").setValueAsync(seatsUpdate)
    }

    fun reserveSeat(screeningId: Long, seatId: Long) {
        ref.child("$screeningId")
                .updateChildrenAsync(mapOf(seatId.toString() to "reserved"))
    }
}