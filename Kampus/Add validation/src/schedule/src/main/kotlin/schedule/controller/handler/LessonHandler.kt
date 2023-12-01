package schedule.controller.handler

import io.ktor.server.application.*

interface LessonHandler {
    suspend fun create(call: ApplicationCall)
    suspend fun findById(call: ApplicationCall, id: Int)
}
