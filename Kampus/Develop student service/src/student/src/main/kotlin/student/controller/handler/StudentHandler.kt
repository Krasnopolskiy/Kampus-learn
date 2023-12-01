package student.controller.handler

import io.ktor.server.application.*

interface StudentHandler {
    suspend fun create(call: ApplicationCall)
    suspend fun findAll(call: ApplicationCall)
    suspend fun findById(call: ApplicationCall)
}
