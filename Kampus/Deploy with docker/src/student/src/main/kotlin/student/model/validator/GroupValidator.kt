package student.model.validator

import io.ktor.server.plugins.requestvalidation.*
import student.model.dto.group.GroupRequest
import student.model.dto.student.StudentRequest

fun RequestValidationConfig.configureGroupValidator() {
    validate<GroupRequest> { group ->
        if (group.name.isEmpty())
            ValidationResult.Invalid("Group name cannot be empty")
        else ValidationResult.Valid
    }
}
