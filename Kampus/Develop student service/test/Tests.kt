import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.jupiter.api.TestInstance
import kotlin.random.Random
import kotlin.test.assertContains
import kotlin.test.assertEquals


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Test {
    private val baseUrl = "http://localhost:8000"
    private val client = HttpClient()


    @Test
    fun `Student can be created`() = runBlocking {
        val name = random("Student")
        val response = createStudent(name)
        assertContains(response.bodyAsText(), name)
    }

    @Test
    fun `Students can be listed`() = runBlocking {
        val name = random("Student").also { createStudent(it) }
        val response = client.get("$baseUrl/students")
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
        val response = client.get("$baseUrl/groups")
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
        val response = client.get("$baseUrl/students/$studentId")
        assertContains(response.bodyAsText(), groupName)
    }

    private fun random(prefix: String) = "$prefix #${Random.nextInt(100, 1000)}"

    private suspend fun createStudent(name: String): HttpResponse {
        val email = name.takeLastWhile { it != '#' }
        val request = """{"name": "$name", "email": "$email@mail.com"}"""
        return client.post("$baseUrl/students") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }
    }

    private suspend fun createGroup(name: String): HttpResponse {
        val request = """{"name": "$name"}"""
        return client.post("$baseUrl/groups") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }
    }

    private suspend fun joinGroup(groupId: Int, studentId: Int): HttpResponse {
        val request = """{"studentId": "$studentId"}"""
        return client.post("$baseUrl/groups/$groupId/join") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }
    }

    private suspend fun getId(response: HttpResponse) =
        Regex(""""id": (\d+)""").find(response.bodyAsText())
            ?.groups?.lastOrNull()?.value?.toIntOrNull() ?: -1
}
