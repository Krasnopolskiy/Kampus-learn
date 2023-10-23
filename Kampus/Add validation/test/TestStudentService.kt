import AbstractTest.Companion.BASE_URL
import AbstractTest.Companion.INVALID_ID
import AbstractTest.Companion.NON_EXISTENT_ID
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.jupiter.api.TestInstance
import kotlin.test.assertContains
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestStudentService : AbstractTest {
    override val studentUrl = "$BASE_URL:8000"
    override val client = HttpClient()

    @Test
    fun `Student can be created`() = runBlocking {
        val name = random("Student")
        val response = createStudent(name)
        assertContains(response.bodyAsText(), name)
    }

    @Test
    fun `Student can be fetched`() = runBlocking {
        val name = random("Student")
        val id = getId(createStudent(name))
        val response = client.get("$studentUrl/students/$id")
        assertContains(response.bodyAsText(), name)
    }

    @Test
    fun `Students can be listed`() = runBlocking {
        val name = random("Student").also { createStudent(it) }
        val response = client.get("$studentUrl/students")
        assertContains(response.bodyAsText(), name)
    }

    @Test
    fun `Group can be created`() = runBlocking {
        val name = random("Group")
        val response = createGroup(name)
        assertContains(response.bodyAsText(), name)
    }

    @Test
    fun `Groups can be listed`() = runBlocking {
        val name = random("Group").also { createGroup(it) }
        val response = client.get("$studentUrl/groups")
        assertContains(response.bodyAsText(), name)
    }

    @Test
    fun `Student can join group`() = runBlocking {
        val groupId = getId(createGroup(random("Group")))
        val studentId = getId(createStudent(random("Student")))
        val response = joinGroup(groupId, studentId)
        assertEquals(HttpStatusCode.OK, response.status)
    }

    @Test
    fun `Student can view joined group`() = runBlocking {
        val groupName = random("Group")
        val groupId = getId(createGroup(groupName))
        val studentId = getId(createStudent(random("Student")))
        joinGroup(groupId, studentId)
        val response = client.get("$studentUrl/students/$studentId")
        assertContains(response.bodyAsText(), groupName)
    }

    @Test
    fun `Student with empty name cannot be created`() = runBlocking {
        val request = """{"name": "", "email": "mock@mail.com"}"""
        val response = client.sendJson("$studentUrl/students", request)
        assertEquals(HttpStatusCode.BadRequest, response.status)
        assertContains(response.bodyAsText(), "Student name cannot be empty")
    }

    @Test
    fun `Student with empty email cannot be created`() = runBlocking {
        val request = """{"name": "mock", "email": ""}"""
        val response = client.sendJson("$studentUrl/students", request)
        assertEquals(HttpStatusCode.BadRequest, response.status)
        assertContains(response.bodyAsText(), "Student email cannot be empty")
    }

    @Test
    fun `Non-existent student cannot be fetched`() = runBlocking {
        val response = client.get("$studentUrl/students/$NON_EXISTENT_ID")
        assertEquals(HttpStatusCode.NotFound, response.status)
    }

    @Test
    fun `Student with invalid id cannot be fetched`() = runBlocking {
        val response = client.get("$studentUrl/students/$INVALID_ID")
        assertEquals(HttpStatusCode.BadRequest, response.status)
    }

    @Test
    fun `Group with empty name cannot be created`() = runBlocking {
        val request = """{"name": ""}"""
        val response = client.sendJson("$studentUrl/groups", request)
        assertEquals(HttpStatusCode.BadRequest, response.status)
        assertContains(response.bodyAsText(), "Group name cannot be empty")
    }

    @Test
    fun `Student cannot join non-existent group`() = runBlocking {
        val studentId = getId(createStudent(random("Student")))
        val response = joinGroup(NON_EXISTENT_ID, studentId)
        assertEquals(HttpStatusCode.NotFound, response.status)
    }

    private suspend fun createStudent(name: String): HttpResponse {
        val email = name.takeLastWhile { it != '#' }
        val request = """{"name": "$name", "email": "$email@mail.com"}"""
        return client.sendJson("$studentUrl/students", request)
    }

    private suspend fun joinGroup(groupId: Int, studentId: Int): HttpResponse {
        val request = """{"studentId": "$studentId"}"""
        return client.sendJson("$studentUrl/groups/$groupId/join", request)
    }
}
