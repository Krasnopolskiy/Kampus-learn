package schedule.plugin

import io.ktor.server.application.*
import io.ktor.server.plugins.requestvalidation.*
import schedule.model.validator.configureLessonValidator

fun Application.configureRequestValidation() {
    install(RequestValidation) {
        configureLessonValidator()
    }
}
