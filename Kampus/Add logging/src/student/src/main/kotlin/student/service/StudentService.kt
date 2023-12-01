package student.service

import student.model.dto.student.StudentFullResponse
import student.model.dto.student.StudentRequest
import student.model.dto.student.StudentResponse

interface StudentService {
    fun create(request: StudentRequest): StudentResponse
    fun findAll(): List<StudentResponse>
    fun findById(id: Int): StudentFullResponse
}
