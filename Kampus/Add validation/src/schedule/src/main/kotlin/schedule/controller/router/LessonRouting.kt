package schedule.controller.router

import io.ktor.server.application.*
import io.ktor.server.resources.*
import io.ktor.server.resources.post
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import schedule.controller.handler.LessonHandler
import schedule.controller.resource.Lessons

fun Route.lessonRouting() {
    val handler by inject<LessonHandler>()

    post<Lessons> { handler.create(call) }
    get<Lessons.Id> { lesson -> handler.findById(call, lesson.id) }
}
