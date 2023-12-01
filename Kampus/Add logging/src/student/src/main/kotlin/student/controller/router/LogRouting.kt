package student.controller.router

import io.ktor.server.http.content.*
import io.ktor.server.routing.*
import java.io.File

fun Route.logRouting() {
    staticFiles("/logs", File("logs/student.log"))
}
