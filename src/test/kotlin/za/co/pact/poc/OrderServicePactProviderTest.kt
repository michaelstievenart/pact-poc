package za.co.pact.poc

import au.com.dius.pact.provider.junit.Provider
import au.com.dius.pact.provider.junit.State
import au.com.dius.pact.provider.junit.loader.PactFolder
import au.com.dius.pact.provider.junit5.HttpTestTarget
import au.com.dius.pact.provider.junit5.PactVerificationContext
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestTemplate
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.SpringApplication
import org.springframework.boot.test.context.SpringBootTest

@Suppress("unused")
@Provider("orderService")
@PactFolder("pacts")
@SpringBootTest
internal class OrderServicePactProviderTest {

    val host = "localhost"
    val port = 8080
    val initialPath = "/"

    companion object {
        @BeforeAll
        fun start() {
            SpringApplication.run(PactPocApplication::class.java)
        }
    }

    @BeforeEach
    fun beforeEach(context: PactVerificationContext) {
        context.target = HttpTestTarget(host, port, initialPath)
    }

    @State("order 1 exists")
    fun setupExpectedStates() {
    }

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider::class)
    fun pactVerificationTestTemplate(context: PactVerificationContext) {
        context.verifyInteraction()
    }
}