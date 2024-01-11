package kampus.service

import kampus.model.dto.group.GroupRequest
import kampus.model.dto.group.GroupResponse
import kampus.model.dto.group.JoinRequest
import kampus.model.dto.group.JoinResponse

interface GroupService {
    fun create(request: GroupRequest): GroupResponse
    fun findAll(): List<GroupResponse>
    fun findByIds(ids: List<Int>): List<GroupResponse>
    fun join(id: Int, request: JoinRequest): JoinResponse
    fun findByStudent(studentId: Int): List<GroupResponse>
}
