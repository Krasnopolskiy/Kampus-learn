package kampus.controller.handler.impl

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import kampus.controller.handler.StudentHandler
import kampus.model.dto.student.StudentRequest
import kampus.service.StudentService

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
