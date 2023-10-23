import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.util.*
import kotlin.test.assertContains
import kotlin.test.assertEquals


class TestScheduleService {
    private val baseStudentUrl = "http://localhost:8000"
    private val baseScheduleUrl = "http://localhost:8001"
    private val client = HttpClient()


    @Test
    fun `Lesson can be created`() = runBlocking {
        val name = random("Lesson")
        val response = createLesson(name, emptyList())
        assertContains(response.bodyAsText(), name)
    }

    @Test
    fun `Lesson with group can be created`() = runBlocking {
        val groupName = random("Group")
        val groupId = getId(createGroup(groupName))
        val response = createLesson(random("Lesson"), listOf(groupId))
        assertContains(response.bodyAsText(), groupName)
    }

    @Test
    fun `Lesson with group can be fetched`() = runBlocking {
        val groupName = random("Group")
        val groupId = getId(createGroup(groupName))
        val lessonId = getId(createLesson(random("Lesson"), listOf(groupId)))
        val response = client.get("$baseScheduleUrl/lessons/$lessonId")
        assertContains(response.bodyAsText(), groupName)
    }

    @Test
    fun `Lesson with empty name cannot be created`() = runBlocking {
        val request = """{"name": "", "groupIds": []}"""
        val response = client.sendJson("$baseScheduleUrl/lessons", request)
        assertEquals(HttpStatusCode.BadRequest, response.status)
        assertContains(response.bodyAsText(), "Lesson name cannot be empty")
    }

    @Test
    fun `Non-existent lesson cannot be fetched`() = runBlocking {
        val response = client.get("$baseScheduleUrl/lessons/$NON_EXISTENT_ID")
        assertEquals(HttpStatusCode.NotFound, response.status)
    }

    @Test
    fun `Lesson with invalid id cannot be fetched`() = runBlocking {
        val response = client.get("$baseScheduleUrl/lessons/$INVALID_ID")
        assertEquals(HttpStatusCode.BadRequest, response.status)
    }

    @Test
    fun `Logs can be fetched`() = runBlocking {
        val path = UUID.randomUUID().toString()
        val requestId = UUID.randomUUID().toString()
        client.get("$baseScheduleUrl/$path") {
            headers { append("Request-Id", requestId) }
        }
        val response = client.get("$baseScheduleUrl/logs").bodyAsText()
        assertContains(response, path)
        assertContains(response, requestId)
    }

    private suspend fun createGroup(name: String): HttpResponse {
        val request = """{"name": "$name"}"""
        return client.sendJson("$baseStudentUrl/groups", request)
    }

    private suspend fun createLesson(name: String, groupIds: List<Int>): HttpResponse {
        val groups = groupIds.joinToString(", ")
        val request = """{"name": "$name", "groupIds": [$groups]}"""
        return client.sendJson("$baseScheduleUrl/lessons", request)
    }
}
