package student.service.impl

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import student.database.entity.Student
import student.model.dto.student.StudentFullResponse
import student.model.dto.student.StudentRequest
import student.model.dto.student.StudentResponse
import student.model.mapper.StudentMapper
import student.service.GroupService
import student.service.StudentService

class StudentServiceImpl : StudentService, KoinComponent {
    private val students = mutableSetOf<Student>()

    private val mapper by inject<StudentMapper>()
    private val groupService by inject<GroupService>()

    override fun create(request: StudentRequest): StudentResponse {
        val student = mapper.asEntity(students.size, request)
        students.add(student)
        return mapper.asResponse(student)
    }

    override fun findAll(): List<StudentResponse> {
        return mapper.asListResponse(students)
    }

    override fun findById(id: Int): StudentFullResponse {
        val student = students.find { it.id == id } ?: throw Exception("Not found")
        val groups = groupService.findByStudent(id)
        return mapper.asFullResponse(student, groups)
    }
}
