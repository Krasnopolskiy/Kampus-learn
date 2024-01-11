package kampus.service.impl

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kampus.database.entity.Student
import kampus.model.dto.student.StudentFullResponse
import kampus.model.dto.student.StudentRequest
import kampus.model.dto.student.StudentResponse
import kampus.model.mapper.StudentMapper
import kampus.service.GroupService
import kampus.service.StudentService

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
