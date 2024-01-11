package kampus.controller.router

import org.koin.ktor.ext.inject
import io.ktor.server.application.call
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import kampus.controller.handler.LessonHandler

fun Route.lessonRouting() {
    val handler by inject<LessonHandler>()

    post { handler.create(call) }
    get("/{id}") { handler.findById(call) }
}
