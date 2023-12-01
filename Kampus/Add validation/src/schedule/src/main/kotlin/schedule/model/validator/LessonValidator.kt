package schedule.model.validator

import io.ktor.server.plugins.requestvalidation.*
import schedule.model.dto.lesson.LessonRequest

fun RequestValidationConfig.configureLessonValidator() {
    validate<LessonRequest> { lesson ->
        if (lesson.name.isEmpty())
            ValidationResult.Invalid("Lesson name cannot be empty")
        else ValidationResult.Valid
    }
}
