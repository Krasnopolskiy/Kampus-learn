package kampus.model.mapper

import org.koin.core.component.KoinComponent
import kampus.database.entity.Lesson
import kampus.model.dto.group.GroupResponse
import kampus.model.dto.lesson.LessonRequest
import kampus.model.dto.lesson.LessonResponse

class LessonMapper : KoinComponent {
    fun asEntity(id: Int, request: LessonRequest) = Lesson(
        id = id,
        name = request.name,
        groupIds = request.groupIds,
    )

    fun asResponse(entity: Lesson, groups: List<GroupResponse>) = LessonResponse(
        id = entity.id,
        name = entity.name,
        groups = groups,
    )
}
