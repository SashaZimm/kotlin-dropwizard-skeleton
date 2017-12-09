import io.dropwizard.Application
import io.dropwizard.setup.Bootstrap
import io.dropwizard.setup.Environment
import resource.name.BackendResource

class BackendApplication : Application<BackendConfiguration>() {

    companion object Main {
        @JvmStatic
        @Throws(Exception::class)
        fun main(args: Array<String>) {
            BackendApplication().run(*args)
        }
    }

    override fun run(configuration: BackendConfiguration?, environment: Environment) {
        val backendResource = BackendResource()
        environment.jersey().register(backendResource)
    }

    override fun initialize(bootstrap: Bootstrap<BackendConfiguration>?) {
        super.initialize(bootstrap)
    }

}