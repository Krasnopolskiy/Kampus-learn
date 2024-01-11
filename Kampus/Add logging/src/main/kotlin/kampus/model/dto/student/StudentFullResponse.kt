package kampus.model.dto.student

import kotlinx.serialization.Serializable
import kampus.model.dto.group.GroupResponse

@Serializable
class StudentFullResponse(
    val id: Int,
    val name: String,
    val email: String,
    val groups: List<GroupResponse>,
)
