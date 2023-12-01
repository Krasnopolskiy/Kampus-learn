package schedule.plugin

import io.ktor.server.application.*
import io.ktor.server.routing.*
import schedule.controller.router.lessonRouting

fun Application.configureRouting() = routing {
    route("/lessons") { lessonRouting() }
}
