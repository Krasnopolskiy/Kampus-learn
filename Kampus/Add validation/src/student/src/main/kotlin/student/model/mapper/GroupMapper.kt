package student.model.mapper

import student.database.entity.Group
import student.database.entity.StudentGroup
import student.model.dto.group.GroupRequest
import student.model.dto.group.GroupResponse

class GroupMapper {
    fun asEntity(id: Int, request: GroupRequest) = Group(
        id = id,
        name = request.name,
    )

    fun asEntity(studentId: Int, groupId: Int) = StudentGroup(
        studentId = studentId,
        groupId = groupId
    )

    fun asResponse(entity: Group) = GroupResponse(
        id = entity.id,
        name = entity.name,
    )

    fun asListResponse(entities: Collection<Group>) = entities.map(::asResponse)
}
