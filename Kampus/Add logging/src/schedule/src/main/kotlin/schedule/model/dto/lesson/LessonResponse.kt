package schedule.model.dto.lesson

import kotlinx.serialization.Serializable
import schedule.model.dto.group.Group

@Serializable
class LessonResponse(
    val id: Int,
    val name: String,
    val groups: List<Group>
)
