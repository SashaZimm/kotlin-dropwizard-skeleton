package resource.name

import BackendApplication
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import test.support.DropwizardTestApplication


@RunWith(JUnitPlatform::class)
class BackendResourceSpek : Spek({

    val applicationUnderTest = DropwizardTestApplication(BackendApplication::class)

    beforeGroup {
        applicationUnderTest.start()
    }

    afterGroup {
        applicationUnderTest.stop()
    }

    given("The Dropwizard Application is running") {

        it("should return a default name when one isn't provided as a parameter") {

            applicationUnderTest.client().use { client ->

                val response = client.invokeFor("backend", Name::class)

                assertThat(response.name).isEqualTo("Default name from application")
            }
        }

        it("should return the name when one is provided as a request parameter") {

            applicationUnderTest.client().use { client ->

                val response = client.invokeFor("backend?name=Winston", Name::class)

                assertThat(response.name).isEqualTo("Winston")
            }
        }

    }

})