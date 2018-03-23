package th.ac.kmitl.it.rmtrs

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
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
