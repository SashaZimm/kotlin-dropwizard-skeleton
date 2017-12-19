package consumer

import BackendApplication
import io.github.robwin.swagger.test.SwaggerAssertions
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import test.support.DropwizardTestApplication

@RunWith(JUnitPlatform::class)
class ConsumerDrivenContractSpek : Spek({

    val applicationUnderTest = DropwizardTestApplication(BackendApplication::class)

    beforeGroup {
        applicationUnderTest.start()
    }

    afterGroup {
        applicationUnderTest.stop()
    }

    given("The consumer contract") {

        val consumerContract = javaClass.getResource("/swagger-consumer-contract.yaml").path

        it("should match the Swagger API documentation") {
            SwaggerAssertions.assertThat("http://localhost:8080/swagger.yaml").satisfiesContract(consumerContract)
        }
    }
})