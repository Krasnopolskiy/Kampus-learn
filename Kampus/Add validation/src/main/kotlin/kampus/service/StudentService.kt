package kampus.service

import kampus.model.dto.student.StudentFullResponse
import kampus.model.dto.student.StudentRequest
import kampus.model.dto.student.StudentResponse

interface StudentService {
    fun create(request: StudentRequest): StudentResponse
    fun findAll(): List<StudentResponse>
    fun findById(id: Int): StudentFullResponse
}
