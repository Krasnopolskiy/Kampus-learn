package student.controller.handler.impl

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import student.controller.handler.GroupHandler
import student.model.dto.group.GroupRequest
import student.model.dto.group.JoinRequest
import student.service.GroupService

class GroupHandlerImpl : GroupHandler, KoinComponent {
    private val service by inject<GroupService>()

    override suspend fun create(call: ApplicationCall) {
        val request = call.receive<GroupRequest>()
        val response = service.create(request)
        call.respond(response)
    }

    override suspend fun findAll(call: ApplicationCall) {
        val response = service.findAll()
        call.respond(response)
    }

    override suspend fun join(call: ApplicationCall) {
        val id = call.parameters["id"]?.toInt() ?: 0
        val request = call.receive<JoinRequest>()
        val response = service.join(id, request)
        call.respond(response)
    }
}
