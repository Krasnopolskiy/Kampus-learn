package kampus.plugin

import io.ktor.server.application.Application
import io.ktor.server.routing.routing
import kampus.controller.router.groupRouting
import kampus.controller.router.lessonRouting
import kampus.controller.router.logRouting
import kampus.controller.router.studentRouting

fun Application.configureRouting() = routing {
    studentRouting()
    groupRouting()
    lessonRouting()
    logRouting()
}
