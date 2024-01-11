package kampus.model.dto.lesson

import kotlinx.serialization.Serializable
import kampus.model.dto.group.GroupResponse

@Serializable
class LessonResponse(
    val id: Int,
    val name: String,
    val groups: List<GroupResponse>,
)
