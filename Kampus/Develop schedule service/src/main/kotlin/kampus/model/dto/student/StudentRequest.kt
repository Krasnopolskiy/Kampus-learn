package kampus.model.dto.student

import kotlinx.serialization.Serializable

@Serializable
class StudentRequest(
    val name: String,
    val email: String,
)
