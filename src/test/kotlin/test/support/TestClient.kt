package test.support

import io.dropwizard.Configuration
import io.dropwizard.client.JerseyClientBuilder
import io.dropwizard.testing.DropwizardTestSupport
import java.io.Closeable
import java.util.*
import javax.ws.rs.client.Client
import kotlin.reflect.KClass

class DropwizardTestClient(private val applicationUnderTest: DropwizardTestSupport<out Configuration>) : Closeable {

    private val baseUriTemplate = "http://localhost:%d/%s"

    private val client: Client = JerseyClientBuilder(applicationUnderTest.environment).build(UUID.randomUUID().toString())

    private var isAdmin: Boolean = false

    fun <T : Any> invokeFor(endpoint: String, responseClass: KClass<T>): T {
        return client.target(String.format(baseUriTemplate, port(), endpoint))
                .request()
                .get(responseClass.java)
    }

    fun admin(): DropwizardTestClient {
        isAdmin = true
        return this
    }

    override fun close() {
        client.close()
    }

    private fun port(): Int {
        return if (isAdmin) applicationUnderTest.adminPort else applicationUnderTest.localPort
    }

}