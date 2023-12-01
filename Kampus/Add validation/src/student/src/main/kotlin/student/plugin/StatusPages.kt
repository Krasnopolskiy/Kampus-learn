package student.plugin

import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import student.controller.exception.handleException

fun Application.configureStatusPages() {
    install(StatusPages) {
        exception<Throwable>(::handleException)
    }
}
