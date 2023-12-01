package schedule.plugin

import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import schedule.controller.exception.handleException

fun Application.configureStatusPages() {
    install(StatusPages) {
        exception<Throwable>(::handleException)
    }
}
