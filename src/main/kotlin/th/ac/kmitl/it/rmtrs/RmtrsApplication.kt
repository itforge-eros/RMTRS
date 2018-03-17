package th.ac.kmitl.it.rmtrs

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import th.ac.kmitl.it.rmtrs.model.Seat
import th.ac.kmitl.it.rmtrs.model.SeatType
import th.ac.kmitl.it.rmtrs.model.Theatre
import th.ac.kmitl.it.rmtrs.repository.SeatRepository
import th.ac.kmitl.it.rmtrs.repository.SeatTypeRepository
import th.ac.kmitl.it.rmtrs.repository.TheatreRepository
import javax.annotation.PreDestroy

@SpringBootApplication
@EnableJpaAuditing
class RmtrsApplication(
        val theatreRepository: TheatreRepository,
        val seatRepository: SeatRepository,
        val seatTypeRepository: SeatTypeRepository
) {

    @Bean
    fun run() = CommandLineRunner {
        val theatre = Theatre(name = "AI")
        val type1 = SeatType(name = "Luxury", price = 300.0, description = "XXXXX")
        val seat1 = Seat(row = "A", number = 2)
        val seat2 = Seat(row = "B", number = 3)

        seat1.seatType = type1
        seat2.seatType = type1
        listOf(seat1, seat2).forEach { theatre.addSeat(it)}
        seatTypeRepository.save(type1)
        theatreRepository.save(theatre)
    }

    @PreDestroy
    fun destroy() {
        seatRepository.deleteAll()
        theatreRepository.deleteAll()
        seatTypeRepository.deleteAll()
    }

}

fun main(args: Array<String>) {
    runApplication<RmtrsApplication>(*args)
}
