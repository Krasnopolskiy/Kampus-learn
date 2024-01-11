package kampus.controller.router

import org.koin.ktor.ext.inject
import io.ktor.server.application.call
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import kampus.controller.handler.GroupHandler

fun Route.groupRouting() {
    val handler by inject<GroupHandler>()

    post { handler.create(call) }
    get { handler.findAll(call) }
    post("/{id}/join") { handler.join(call) }
}
