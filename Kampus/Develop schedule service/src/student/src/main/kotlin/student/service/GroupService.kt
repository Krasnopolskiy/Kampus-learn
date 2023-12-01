package student.service

import student.model.dto.group.GroupRequest
import student.model.dto.group.GroupResponse
import student.model.dto.group.JoinRequest
import student.model.dto.group.JoinResponse

interface GroupService {
    fun create(request: GroupRequest): GroupResponse
    fun findAll(): List<GroupResponse>
    fun join(id: Int, request: JoinRequest): JoinResponse
    fun findByStudent(studentId: Int): List<GroupResponse>
}
