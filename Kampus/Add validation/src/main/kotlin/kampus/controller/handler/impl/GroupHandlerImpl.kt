package kampus.controller.handler.impl

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import kampus.controller.handler.GroupHandler
import kampus.model.dto.group.GroupRequest
import kampus.model.dto.group.JoinRequest
import kampus.service.GroupService

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

    override suspend fun join(call: ApplicationCall, id: Int) {
        val request = call.receive<JoinRequest>()
        val response = service.join(id, request)
        call.respond(response)
    }
}
