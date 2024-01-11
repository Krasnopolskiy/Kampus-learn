package kampus.plugin

import io.ktor.server.application.Application
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import kampus.controller.router.scheduleRouting
import kampus.controller.router.studentRouting

fun Application.configureRouting() = routing {
    route("/students") { studentRouting() }
    route("/schedule") { scheduleRouting() }
}
