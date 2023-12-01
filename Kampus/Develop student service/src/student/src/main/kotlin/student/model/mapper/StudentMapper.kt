package student.model.mapper

import org.koin.core.component.KoinComponent
import student.database.entity.Student
import student.model.dto.group.GroupResponse
import student.model.dto.student.StudentFullResponse
import student.model.dto.student.StudentRequest
import student.model.dto.student.StudentResponse

class StudentMapper : KoinComponent {
    fun asEntity(id: Int, request: StudentRequest) = Student(
        id = id,
        name = request.name,
        email = request.email,
    )

    fun asResponse(entity: Student) = StudentResponse(
        id = entity.id,
        name = entity.name,
        email = entity.email,
    )

    fun asFullResponse(entity: Student, groups: List<GroupResponse>) = StudentFullResponse(
        id = entity.id,
        name = entity.name,
        email = entity.email,
        groups = groups,
    )

    fun asListResponse(entities: Collection<Student>) = entities.map(::asResponse)
}
