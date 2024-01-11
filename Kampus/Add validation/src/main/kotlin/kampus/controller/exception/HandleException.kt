package kampus.controller.exception

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.plugins.BadRequestException
import io.ktor.server.plugins.NotFoundException
import io.ktor.server.plugins.requestvalidation.RequestValidationException
import io.ktor.server.response.respond
import kampus.model.dto.ApiError

suspend fun handleException(call: ApplicationCall, cause: Throwable) {
    val status = when (cause) {
        is BadRequestException, is RequestValidationException -> HttpStatusCode.BadRequest
        is NotFoundException -> HttpStatusCode.NotFound
        else -> HttpStatusCode.InternalServerError
    }
    call.response.status(status)
    call.respond(ApiError(cause.localizedMessage))
}
