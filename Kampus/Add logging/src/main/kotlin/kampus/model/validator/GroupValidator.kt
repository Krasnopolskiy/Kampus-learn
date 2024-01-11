package kampus.model.validator

import io.ktor.server.plugins.requestvalidation.RequestValidationConfig
import io.ktor.server.plugins.requestvalidation.ValidationResult
import kampus.model.dto.group.GroupRequest

fun RequestValidationConfig.configureGroupValidator() {
    validate<GroupRequest> { group ->
        if (group.name.isEmpty())
            ValidationResult.Invalid("Group name cannot be empty")
        else ValidationResult.Valid
    }
}
