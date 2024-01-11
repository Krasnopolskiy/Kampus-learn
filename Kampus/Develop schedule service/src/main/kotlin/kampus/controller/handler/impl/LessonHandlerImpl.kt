package kampus.controller.handler.impl

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import kampus.controller.handler.LessonHandler
import kampus.model.dto.lesson.LessonRequest
import kampus.service.LessonService

class LessonHandlerImpl : LessonHandler, KoinComponent {
    private val service by inject<LessonService>()

    override suspend fun create(call: ApplicationCall) {
        val request = call.receive<LessonRequest>()
        val response = service.create(request)
        call.respond(response)
    }

    override suspend fun findById(call: ApplicationCall) {
        val id = call.parameters["id"]?.toInt() ?: 0
        val response = service.findById(id)
        call.respond(response)
    }
}
