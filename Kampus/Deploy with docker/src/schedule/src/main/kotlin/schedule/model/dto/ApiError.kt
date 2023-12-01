package schedule.model.dto

import kotlinx.serialization.Serializable

@Serializable
class ApiError(
    val message: String
)