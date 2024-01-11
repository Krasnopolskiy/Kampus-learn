package kampus.service.impl

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import io.ktor.server.plugins.NotFoundException
import kampus.database.entity.Lesson
import kampus.model.dto.lesson.LessonRequest
import kampus.model.dto.lesson.LessonResponse
import kampus.model.mapper.LessonMapper
import kampus.service.GroupService
import kampus.service.LessonService

class LessonServiceImpl : LessonService, KoinComponent {
    private val lessons = mutableSetOf<Lesson>()

    private val mapper by inject<LessonMapper>()
    private val groupService by inject<GroupService>()

    override fun create(request: LessonRequest): LessonResponse {
        val lesson = mapper.asEntity(lessons.size, request)
        val groups = groupService.findByIds(lesson.groupIds)
        lessons.add(lesson)
        return mapper.asResponse(lesson, groups)
    }

    override fun findById(id: Int): LessonResponse {
        val lesson = lessons.find { it.id == id } ?: throw NotFoundException()
        val groups = groupService.findByIds(lesson.groupIds)
        return mapper.asResponse(lesson, groups)
    }
}
