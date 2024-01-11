package kampus.controller.router

import org.koin.ktor.ext.inject
import io.ktor.server.application.call
import io.ktor.server.resources.get
import io.ktor.server.resources.post
import io.ktor.server.routing.Route
import kampus.controller.handler.LessonHandler
import kampus.controller.resource.Lessons

fun Route.lessonRouting() {
    val handler by inject<LessonHandler>()

    post<Lessons> { handler.create(call) }
    get<Lessons.Id> { lesson -> handler.findById(call, lesson.id) }
}
