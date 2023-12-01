package schedule.model.mapper

import org.koin.core.component.KoinComponent
import schedule.database.entity.Lesson
import schedule.model.dto.group.Group
import schedule.model.dto.lesson.LessonRequest
import schedule.model.dto.lesson.LessonResponse

class LessonMapper : KoinComponent {
    fun asEntity(id: Int, request: LessonRequest) = Lesson(
        id = id,
        name = request.name,
        groupIds = request.groupIds
    )

    fun asResponse(entity: Lesson, groups: List<Group>) = LessonResponse(
        id = entity.id,
        name = entity.name,
        groups = groups
    )
}
