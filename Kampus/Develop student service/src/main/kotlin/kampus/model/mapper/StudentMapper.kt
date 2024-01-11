package kampus.model.mapper

import org.koin.core.component.KoinComponent
import kampus.database.entity.Student
import kampus.model.dto.group.GroupResponse
import kampus.model.dto.student.StudentFullResponse
import kampus.model.dto.student.StudentRequest
import kampus.model.dto.student.StudentResponse

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
