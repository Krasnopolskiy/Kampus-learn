package kampus.controller.handler

import io.ktor.server.application.ApplicationCall

interface StudentHandler {
    suspend fun create(call: ApplicationCall)
    suspend fun findAll(call: ApplicationCall)
    suspend fun findById(call: ApplicationCall)
}
