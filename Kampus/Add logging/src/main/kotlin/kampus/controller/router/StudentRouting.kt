package kampus.controller.router

import org.koin.ktor.ext.inject
import io.ktor.server.application.call
import io.ktor.server.resources.get
import io.ktor.server.resources.post
import io.ktor.server.routing.Route
import kampus.controller.handler.StudentHandler
import kampus.controller.resource.Students

fun Route.studentRouting() {
    val handler by inject<StudentHandler>()

    get<Students> { handler.findAll(call) }
    post<Students> { handler.create(call) }
    get<Students.Id> { student -> handler.findById(call, student.id) }
}
