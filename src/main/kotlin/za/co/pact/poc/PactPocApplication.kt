package za.co.pact.poc

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PactPocApplication

fun main(args: Array<String>) {
	runApplication<PactPocApplication>(*args)
}
