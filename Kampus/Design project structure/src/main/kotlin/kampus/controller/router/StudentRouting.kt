package kampus.controller.router

import io.ktor.server.application.call
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.get

fun Route.studentRouting() {
    get { call.respondText("Hello from Student Service!") }
}
