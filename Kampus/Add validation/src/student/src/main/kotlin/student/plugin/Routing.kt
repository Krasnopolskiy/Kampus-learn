package student.plugin

import io.ktor.server.application.*
import io.ktor.server.routing.*
import student.controller.router.groupRouting
import student.controller.router.studentRouting

fun Application.configureRouting() = routing {
    studentRouting()
    groupRouting()
}
