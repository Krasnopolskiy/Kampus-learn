package kampus.controller.router

import org.koin.ktor.ext.inject
import io.ktor.server.application.call
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import kampus.controller.handler.StudentHandler

fun Route.studentRouting() {
    val handler by inject<StudentHandler>()

    post { handler.create(call) }
    get { handler.findAll(call) }
    get("/{id}") { handler.findById(call) }
}
