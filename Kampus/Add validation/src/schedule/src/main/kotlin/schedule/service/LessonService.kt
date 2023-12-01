package schedule.service

import schedule.model.dto.lesson.LessonRequest
import schedule.model.dto.lesson.LessonResponse

interface LessonService {
    fun create(request: LessonRequest): LessonResponse
    fun findById(id: Int): LessonResponse
}
