package student.controller.handler

import io.ktor.server.application.*

interface GroupHandler {
    suspend fun create(call: ApplicationCall)
    suspend fun findAll(call: ApplicationCall)
    suspend fun join(call: ApplicationCall, id: Int)
}
