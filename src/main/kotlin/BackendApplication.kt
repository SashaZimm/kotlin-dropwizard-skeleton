
import io.dropwizard.Application
import io.dropwizard.setup.Bootstrap
import io.dropwizard.setup.Environment
import io.swagger.jaxrs.config.BeanConfig
import io.swagger.jaxrs.listing.ApiListingResource
import io.swagger.jaxrs.listing.SwaggerSerializers
import resource.name.BackendResource



class BackendApplication : Application<BackendConfiguration>() {

    companion object Main {
        @JvmStatic
        @Throws(Exception::class)
        fun main(args: Array<String>) {
            BackendApplication().run(*args)
        }
    }

    override fun run(configuration: BackendConfiguration, environment: Environment) {
        val backendResource = BackendResource()
        environment.jersey().register(backendResource)
        environment.jersey().register(ApiListingResource())
        environment.jersey().register(SwaggerSerializers())

        initializeSwagger(configuration.swaggerConfiguration)
    }

    override fun initialize(bootstrap: Bootstrap<BackendConfiguration>) {
        super.initialize(bootstrap)

    }

    private fun initializeSwagger(configuration: SwaggerConfiguration) {
        val beanConfig = BeanConfig()
        beanConfig.version = configuration.applicationVersion
        beanConfig.schemes = configuration.schemes
        beanConfig.host = configuration.host
        beanConfig.basePath = configuration.basePath
        beanConfig.resourcePackage = configuration.resourcePackage
        beanConfig.scan = configuration.scanForResources
    }

}