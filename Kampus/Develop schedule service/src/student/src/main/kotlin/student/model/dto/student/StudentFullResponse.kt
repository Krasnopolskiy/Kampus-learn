package student.model.dto.student

import kotlinx.serialization.Serializable
import student.model.dto.group.GroupResponse

@Serializable
class StudentFullResponse(
    val id: Int,
    val name: String,
    val email: String,
    val groups: List<GroupResponse>,
)
