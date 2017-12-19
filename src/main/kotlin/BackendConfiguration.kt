import com.fasterxml.jackson.annotation.JsonProperty
import io.dropwizard.Configuration
import org.hibernate.validator.constraints.NotEmpty

class BackendConfiguration(
        @JsonProperty("swaggerConfiguration") @NotEmpty val swaggerConfiguration: SwaggerConfiguration
) : Configuration()

class SwaggerConfiguration(
        @JsonProperty("applicationVersion") @NotEmpty val applicationVersion: String,
        @JsonProperty("schemes") @NotEmpty val schemes: Array<String>,
        @JsonProperty("host") @NotEmpty val host: String,
        @JsonProperty("basePath") @NotEmpty val basePath: String,
        @JsonProperty("resourcePackage") @NotEmpty val resourcePackage: String,
        @JsonProperty("scanForResources") @NotEmpty val scanForResources: Boolean
)