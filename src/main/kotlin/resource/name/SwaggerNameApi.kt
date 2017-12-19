package resource.name

import io.swagger.annotations.*
import java.util.*

@Api(value = "/backend", description = "Operations on a name resource")
interface SwaggerNameApi {

    @ApiOperation(
            value = "Get a Name entity",
            notes = "Accepts an optional name or uses a default if not provided",
            response = Name::class
    )
    @ApiResponses(
            value = [
                (ApiResponse(code = 400, message = "Something wrong with request"))
            ]
    )
    fun name(@ApiParam(value = "Optional name for the entity to be returned", required = false) name: Optional<String>): Name
}