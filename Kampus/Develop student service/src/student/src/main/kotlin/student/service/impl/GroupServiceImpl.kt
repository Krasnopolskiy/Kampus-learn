package student.service.impl

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import student.database.entity.Group
import student.database.entity.StudentGroup
import student.model.dto.group.GroupRequest
import student.model.dto.group.GroupResponse
import student.model.dto.group.JoinRequest
import student.model.dto.group.JoinResponse
import student.model.mapper.GroupMapper
import student.service.GroupService

class GroupServiceImpl : GroupService, KoinComponent {
    private val groups = mutableSetOf<Group>()
    private val studentGroups = mutableSetOf<StudentGroup>()

    private val mapper by inject<GroupMapper>()

    override fun create(request: GroupRequest): GroupResponse {
        val group = mapper.asEntity(groups.size, request)
        groups.add(group)
        return mapper.asResponse(group)
    }

    override fun findAll(): List<GroupResponse> {
        return mapper.asListResponse(groups)
    }

    override fun join(id: Int, request: JoinRequest): JoinResponse {
        if (id !in groups.indices)
            throw Exception("Not found")
        val studentGroup = mapper.asEntity(request.studentId, id)
        studentGroups.add(studentGroup)
        return JoinResponse(joined = true)
    }

    override fun findByStudent(studentId: Int): List<GroupResponse> {
        val groups = studentGroups
            .filter { it.studentId == studentId }
            .mapNotNull { sg ->
                groups.find { group -> group.id == sg.groupId }
            }
        return mapper.asListResponse(groups)
    }
}
