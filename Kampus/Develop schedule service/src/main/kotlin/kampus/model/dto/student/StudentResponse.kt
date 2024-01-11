package kampus.model.dto.student

import kotlinx.serialization.Serializable

@Serializable
class StudentResponse(
    val id: Int,
    val name: String,
    val email: String,
)
