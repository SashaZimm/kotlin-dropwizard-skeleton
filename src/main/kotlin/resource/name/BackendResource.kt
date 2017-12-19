package resource.name

import com.codahale.metrics.annotation.Timed
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
class BackendResource : SwaggerNameApi {

    @GET
    @Timed
    override fun name(@QueryParam("name") name: Optional<String>): Name {
        return Name(UUID.randomUUID().toString(), name.orElse("Default name from application"))
    }
}