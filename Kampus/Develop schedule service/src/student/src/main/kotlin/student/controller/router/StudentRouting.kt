package student.controller.router

import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import student.controller.handler.StudentHandler

fun Route.studentRouting() {
    val handler by inject<StudentHandler>()

    post { handler.create(call) }
    get { handler.findAll(call) }
    get("/{id}") { handler.findById(call) }
}
