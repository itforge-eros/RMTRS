package th.ac.kmitl.it.rmtrs

import org.springframework.beans.factory.annotation.Value
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
class RmtrsApplication {


    @Bean
    fun run() = CommandLineRunner {

    }

    @PreDestroy
    fun destroy() {

    }

}

fun main(args: Array<String>) {
    runApplication<RmtrsApplication>(*args)
}
