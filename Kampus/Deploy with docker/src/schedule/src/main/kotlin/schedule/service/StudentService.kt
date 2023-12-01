package schedule.service

import retrofit2.http.GET
import schedule.model.dto.group.Group

interface StudentService {
    @GET("/groups")
    suspend fun listGroups(): List<Group>
}
