package test.support

import io.dropwizard.Application
import io.dropwizard.Configuration
import io.dropwizard.testing.DropwizardTestSupport
import io.dropwizard.testing.ResourceHelpers
import javax.annotation.concurrent.NotThreadSafe
import kotlin.reflect.KClass

@NotThreadSafe
class DropwizardTestApplication<C : Configuration, A : Application<C>>(applicationClass: KClass<A>) {

    private val applicationUnderTest = DropwizardTestSupport<C>(
            applicationClass.java,
            ResourceHelpers.resourceFilePath("backend-test.yaml")
    )

    private var isStarted = false

    fun client(): DropwizardTestClient {
        if (isStarted) return DropwizardTestClient(applicationUnderTest) else throw IllegalStateException("You must call start() prior to creating a client")
    }

    fun start() {
        applicationUnderTest.before()
        isStarted = true
    }

    fun stop() {
        applicationUnderTest.after()
        isStarted = false
    }

}