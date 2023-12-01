package student.controller.router

import io.ktor.server.application.*
import io.ktor.server.resources.*
import io.ktor.server.resources.post
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import student.controller.handler.GroupHandler
import student.controller.resource.Groups

fun Route.groupRouting() {
    val handler by inject<GroupHandler>()

    get<Groups> { handler.findAll(call) }
    post<Groups> { handler.create(call) }
    post<Groups.Join> { group -> handler.join(call, group.id) }
}
