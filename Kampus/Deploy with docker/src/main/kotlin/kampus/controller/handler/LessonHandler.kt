package kampus.controller.handler

import io.ktor.server.application.ApplicationCall

interface LessonHandler {
    suspend fun create(call: ApplicationCall)
    suspend fun findById(call: ApplicationCall, id: Int)
}
