package student.controller.router

import io.ktor.server.application.*
import io.ktor.server.resources.*
import io.ktor.server.resources.post
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import student.controller.handler.StudentHandler
import student.controller.resource.Students

fun Route.studentRouting() {
    val handler by inject<StudentHandler>()

    get<Students> { handler.findAll(call) }
    post<Students> { handler.create(call) }
    get<Students.Id> { student -> handler.findById(call, student.id) }
}
