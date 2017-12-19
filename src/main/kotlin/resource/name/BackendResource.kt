package resource.name

import com.codahale.metrics.annotation.Timed
import io.swagger.annotations.*
import java.util.*
import javax.annotation.concurrent.ThreadSafe
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import javax.ws.rs.core.MediaType

@Path("/backend")
@Produces(MediaType.APPLICATION_JSON)
@ThreadSafe
@Api(value = "/backend", description = "Operations on a name resource")
class BackendResource {

    @GET
    @Timed
    @ApiOperation(
            value = "Get a Name entity",
            notes = "Accepts an optional name or uses a default if not provided",
            response = Name::class
    )
    @ApiResponses(
            value = [
                ApiResponse(code = 400, message = "Something wrong with request")
            ]
    )
    fun name(@QueryParam("name")
             @ApiParam(value = "Optional name for the entity to be returned", required = false)
             name: Optional<String>): Name {
        return Name(UUID.randomUUID().toString(), name.orElse("Default name from application"))
    }
}