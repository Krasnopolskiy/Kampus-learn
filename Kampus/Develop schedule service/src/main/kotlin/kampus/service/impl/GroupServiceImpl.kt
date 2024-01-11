package kampus.service.impl

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kampus.database.entity.Group
import kampus.database.entity.StudentGroup
import kampus.model.dto.group.GroupRequest
import kampus.model.dto.group.GroupResponse
import kampus.model.dto.group.JoinRequest
import kampus.model.dto.group.JoinResponse
import kampus.model.mapper.GroupMapper
import kampus.service.GroupService

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

    override fun findByIds(ids: List<Int>): List<GroupResponse> {
        val found = ids.mapNotNull { id -> groups.firstOrNull { it.id == id } }
        return mapper.asListResponse(found)
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
