package student.plugin

import io.ktor.server.application.*
import io.ktor.server.plugins.requestvalidation.*
import student.model.validator.configureGroupValidator
import student.model.validator.configureStudentValidator

fun Application.configureRequestValidation() {
    install(RequestValidation) {
        configureStudentValidator()
        configureGroupValidator()
    }
}
