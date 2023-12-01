package student.controller.exception

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.plugins.requestvalidation.*
import io.ktor.server.response.*
import student.model.dto.ApiError

suspend fun handleException(call: ApplicationCall, cause: Throwable) {
    val status = when (cause) {
        is BadRequestException, is RequestValidationException -> HttpStatusCode.BadRequest
        is NotFoundException -> HttpStatusCode.NotFound
        else -> HttpStatusCode.InternalServerError
    }
    call.response.status(status)
    call.respond(ApiError(cause.localizedMessage))
}
