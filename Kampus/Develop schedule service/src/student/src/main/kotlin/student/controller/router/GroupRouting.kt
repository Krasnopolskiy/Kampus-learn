package student.controller.router

import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import student.controller.handler.GroupHandler

fun Route.groupRouting() {
    val handler by inject<GroupHandler>()

    post { handler.create(call) }
    get { handler.findAll(call) }
    post("/{id}/join") { handler.join(call) }
}
