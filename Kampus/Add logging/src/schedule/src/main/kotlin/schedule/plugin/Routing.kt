package schedule.plugin

import io.ktor.server.application.*
import io.ktor.server.routing.*
import schedule.controller.router.lessonRouting
import schedule.controller.router.logRouting

fun Application.configureRouting() = routing {
    lessonRouting()
    logRouting()
}
