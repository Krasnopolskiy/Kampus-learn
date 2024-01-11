package kampus.controller.router

import org.koin.ktor.ext.inject
import io.ktor.server.application.call
import io.ktor.server.resources.get
import io.ktor.server.resources.post
import io.ktor.server.routing.Route
import kampus.controller.handler.GroupHandler
import kampus.controller.resource.Groups

fun Route.groupRouting() {
    val handler by inject<GroupHandler>()

    get<Groups> { handler.findAll(call) }
    post<Groups> { handler.create(call) }
    post<Groups.Join> { group -> handler.join(call, group.id) }
}
