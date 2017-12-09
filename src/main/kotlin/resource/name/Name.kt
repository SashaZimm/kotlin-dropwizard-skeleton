package resource.name

import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.Length

data class Name(@JsonProperty("id") val id: String, @JsonProperty("name") @Length(max = 20) val name: String)