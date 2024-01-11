package kampus.service

import kampus.model.dto.lesson.LessonRequest
import kampus.model.dto.lesson.LessonResponse

interface LessonService {
    fun create(request: LessonRequest): LessonResponse
    fun findById(id: Int): LessonResponse
}
