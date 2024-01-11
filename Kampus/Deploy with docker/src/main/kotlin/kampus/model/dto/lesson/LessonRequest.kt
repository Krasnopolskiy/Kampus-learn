package kampus.model.dto.lesson

import kotlinx.serialization.Serializable

@Serializable
class LessonRequest(
    val name: String,
    val groupIds: List<Int>,
)
