import com.fasterxml.jackson.annotation.JsonProperty
import io.dropwizard.Configuration
import org.hibernate.validator.constraints.NotEmpty

data class BackendConfiguration(@JsonProperty("defaultName") @NotEmpty val defaultName: String) : Configuration()