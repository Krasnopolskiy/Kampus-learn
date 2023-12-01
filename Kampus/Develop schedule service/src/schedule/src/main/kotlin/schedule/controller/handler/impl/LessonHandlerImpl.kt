package schedule.controller.handler.impl

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import schedule.controller.handler.LessonHandler
import schedule.model.dto.lesson.LessonRequest
import schedule.service.LessonService

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
