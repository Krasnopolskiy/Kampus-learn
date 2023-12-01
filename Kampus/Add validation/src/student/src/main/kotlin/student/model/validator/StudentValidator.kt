package student.model.validator

import io.ktor.server.plugins.requestvalidation.*
import student.model.dto.student.StudentRequest

fun RequestValidationConfig.configureStudentValidator() {
    validate<StudentRequest> { student ->
        if (student.name.isEmpty())
            ValidationResult.Invalid("Student name cannot be empty")
        else if (student.email.isEmpty())
            ValidationResult.Invalid("Student email cannot be empty")
        else ValidationResult.Valid
    }
}
