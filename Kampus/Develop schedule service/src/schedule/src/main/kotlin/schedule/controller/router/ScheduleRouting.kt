package schedule.controller.router

import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import schedule.controller.handler.LessonHandler

fun Route.lessonRouting() {
    val handler by inject<LessonHandler>()

    post { handler.create(call) }
    get("/{id}") { handler.findById(call) }
}
