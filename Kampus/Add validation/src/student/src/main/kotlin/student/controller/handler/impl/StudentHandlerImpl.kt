package student.controller.handler.impl

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import student.controller.handler.StudentHandler
import student.model.dto.student.StudentRequest
import student.service.StudentService

class StudentHandlerImpl : StudentHandler, KoinComponent {
    private val service by inject<StudentService>()

    override suspend fun create(call: ApplicationCall) {
        val request = call.receive<StudentRequest>()
        val response = service.create(request)
        call.respond(response)
    }

    override suspend fun findAll(call: ApplicationCall) {
        val response = service.findAll()
        call.respond(response)
    }

    override suspend fun findById(call: ApplicationCall, id: Int) {
        val response = service.findById(id)
        call.respond(response)
    }
}
