package schedule.service.impl

import kotlinx.coroutines.runBlocking
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import schedule.config.RetrofitManager
import schedule.database.entity.Lesson
import schedule.model.dto.group.Group
import schedule.model.dto.lesson.LessonRequest
import schedule.model.dto.lesson.LessonResponse
import schedule.model.mapper.LessonMapper
import schedule.service.LessonService

class LessonServiceImpl : LessonService, KoinComponent {
    private val lessons = mutableSetOf<Lesson>()

    private val mapper by inject<LessonMapper>()

    override fun create(request: LessonRequest): LessonResponse {
        val lesson = mapper.asEntity(lessons.size, request)
        val groups = getLessonGroups(lesson)
        lessons.add(lesson)
        return mapper.asResponse(lesson, groups)
    }

    override fun findById(id: Int): LessonResponse {
        val lesson = lessons.find { it.id == id } ?: throw Exception("Not found")
        val groups = getLessonGroups(lesson)
        return mapper.asResponse(lesson, groups)
    }

    private fun getLessonGroups(lesson: Lesson): List<Group> {
        val service = RetrofitManager.getStudentService()
        val groups = runBlocking { service.listGroups() }
        return lesson.groupIds.mapNotNull { id -> groups.firstOrNull { it.id == id } }
    }
}
