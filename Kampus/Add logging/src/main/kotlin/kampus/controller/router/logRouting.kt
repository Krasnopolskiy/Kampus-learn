package kampus.controller.router

import io.ktor.server.http.content.staticFiles
import io.ktor.server.routing.Route
import java.io.File

fun Route.logRouting() {
    staticFiles("/logs", File("logs/kampus.log"))
}
