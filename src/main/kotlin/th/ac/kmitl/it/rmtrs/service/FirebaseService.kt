package th.ac.kmitl.it.rmtrs.service

import com.google.firebase.database.DatabaseReference
import org.springframework.stereotype.Service

@Service
class FirebaseService(val ref: DatabaseReference) {

    fun pushReserved(screeningId: Long, seatId: Long) {
        ref.child("$screeningId/reservedList")
                .push().setValueAsync(seatId)
    }

    fun createScreening(screeningId: Long) {
        ref.child("$screeningId")
                .setValueAsync(mapOf("reservedList" to listOf(0), "selectedList" to listOf(0)))
    }

}