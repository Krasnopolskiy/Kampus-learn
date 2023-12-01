package student.model.dto.group

import kotlinx.serialization.Serializable

@Serializable
class GroupResponse(
    val id: Int,
    val name: String,
)
