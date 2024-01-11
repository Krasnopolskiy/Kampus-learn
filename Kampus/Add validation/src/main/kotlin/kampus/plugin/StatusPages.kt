package kampus.plugin

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.statuspages.StatusPages
import kampus.controller.exception.handleException

fun Application.configureStatusPages() {
    install(StatusPages) {
        exception<Throwable>(::handleException)
    }
}
