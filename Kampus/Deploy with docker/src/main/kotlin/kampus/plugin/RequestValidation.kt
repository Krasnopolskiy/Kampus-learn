package kampus.plugin

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.requestvalidation.RequestValidation
import kampus.model.validator.configureGroupValidator
import kampus.model.validator.configureLessonValidator
import kampus.model.validator.configureStudentValidator

fun Application.configureRequestValidation() {
    install(RequestValidation) {
        configureStudentValidator()
        configureGroupValidator()
        configureLessonValidator()
    }
}
