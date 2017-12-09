package resource.admin

import BackendApplication
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import test.support.DropwizardTestApplication
import javax.ws.rs.core.Response


@RunWith(JUnitPlatform::class)
class AdminResourceSpek : Spek({

    val applicationUnderTest = DropwizardTestApplication(BackendApplication::class)

    beforeGroup {
        applicationUnderTest.start()
    }

    afterGroup {
        applicationUnderTest.stop()
    }

    given("The Dropwizard Application is running") {

        it("should pass a health check") {

            applicationUnderTest.client().admin().use { client ->

                val response = client.invokeFor("healthcheck", Response::class)

                assertThat(response.status).isEqualTo(200)
            }
        }

        it("should provide metrics") {

            applicationUnderTest.client().admin().use { client ->

                val response = client.invokeFor("metrics", Map::class)

                assertThat(response.keys).containsExactly("version", "gauges", "counters", "histograms", "meters", "timers")
            }
        }

    }

})