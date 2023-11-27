import AbstractTest.Companion.BASE_URL
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import org.hyperskill.hstest.dynamic.DynamicTest
import org.hyperskill.hstest.stage.StageTest
import org.hyperskill.hstest.testcase.CheckResult
import org.junit.jupiter.api.TestInstance
import kotlin.test.assertContains
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestStudentService : AbstractTest, StageTest<Any>() {
    override val studentUrl = "$BASE_URL:8000"
    override val client = HttpClient()

    @DynamicTest
    fun `Student can be created`(): CheckResult {
        var result: CheckResult = CheckResult.correct()
        runBlocking {
            try {
                val name = random("Student")
                val response = createStudent(name)
                assertContains(response.bodyAsText(), name)
            } catch (exc: Throwable) {
                result = CheckResult.wrong(exc.message)
            }
        }
        return result
    }

    @DynamicTest
    fun `Student can be fetched`(): CheckResult {
        var result: CheckResult = CheckResult.correct()
        runBlocking {
            try {
                val name = random("Student")
                val id = getId(createStudent(name))
                val response = client.get("$studentUrl/students/$id")
                assertContains(response.bodyAsText(), name)
            } catch (exc: Throwable) {
                result = CheckResult.wrong(exc.message)
            }
        }
        return result
    }

    @DynamicTest
    fun `Students can be listed`(): CheckResult {
        var result: CheckResult = CheckResult.correct()
        runBlocking {
            try {
                val name = random("Student").also { createStudent(it) }
                val response = client.get("$studentUrl/students")
                assertContains(response.bodyAsText(), name)
            } catch (exc: Throwable) {
                result = CheckResult.wrong(exc.message)
            }
        }
        return result
    }

    @DynamicTest
    fun `Group can be created`(): CheckResult {
        var result: CheckResult = CheckResult.correct()
        runBlocking {
            try {
                val name = random("Group")
                val response = createGroup(name)
                assertContains(response.bodyAsText(), name)
            } catch (exc: Throwable) {
                result = CheckResult.wrong(exc.message)
            }
        }
        return result
    }

    @DynamicTest
    fun `Groups can be listed`(): CheckResult {
        var result: CheckResult = CheckResult.correct()
        runBlocking {
            try {
                val name = random("Group").also { createGroup(it) }
                val response = client.get("$studentUrl/groups")
                assertContains(response.bodyAsText(), name)
            } catch (exc: Throwable) {
                result = CheckResult.wrong(exc.message)
            }
        }
        return result
    }

    @DynamicTest
    fun `Student can join group`(): CheckResult {
        var result: CheckResult = CheckResult.correct()
        runBlocking {
            try {
                val groupId = getId(createGroup(random("Group")))
                val studentId = getId(createStudent(random("Student")))
                val response = joinGroup(groupId, studentId)
                assertEquals(HttpStatusCode.OK, response.status)
            } catch (exc: Throwable) {
                result = CheckResult.wrong(exc.message)
            }
        }
        return result
    }

    @DynamicTest
    fun `Student can view joined group`(): CheckResult {
        var result: CheckResult = CheckResult.correct()
        runBlocking {
            try {
                val groupName = random("Group")
                val groupId = getId(createGroup(groupName))
                val studentId = getId(createStudent(random("Student")))
                joinGroup(groupId, studentId)
                val response = client.get("$studentUrl/students/$studentId")
                assertContains(response.bodyAsText(), groupName)
            } catch (exc: Throwable) {
                result = CheckResult.wrong(exc.message)
            }
        }
        return result
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
