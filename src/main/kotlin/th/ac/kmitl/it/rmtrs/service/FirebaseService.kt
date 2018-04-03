package th.ac.kmitl.it.rmtrs.service

import com.google.firebase.database.DatabaseReference
import org.springframework.stereotype.Service

@Service
class FirebaseService(val ref: DatabaseReference) {

    fun pushReserved(screeningId: Long, seatId: Long) {
        ref.child("$screeningId/reservedList")
                .push().setValueAsync(seatId)
    }
}