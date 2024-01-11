import org.hyperskill.hstest.testcase.CheckResult
import org.junit.jupiter.api.TestInstance
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.server.testing.ApplicationTestBuilder
import io.ktor.server.testing.testApplication
import kotlin.random.Random

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
interface KampusTest {

    fun appTest(test: suspend ApplicationTestBuilder.() -> Unit): CheckResult {
        var result: CheckResult = CheckResult.correct()
        testApplication {
            try {
                test()
            } catch (exc: Throwable) {
                result = CheckResult.wrong(exc.message)
            }
        }
        return result
    }

    suspend fun HttpClient.createGroup(name: String): HttpResponse {
        val request = """{"name": "$name"}"""
        return sendJson("/groups", request)
    }

    suspend fun HttpClient.createStudent(name: String): HttpResponse {
        val email = name.takeLastWhile { it != '#' }
        val request = """{"name": "$name", "email": "$email@mail.com"}"""
        return sendJson("/students", request)
    }

    suspend fun HttpClient.joinGroup(groupId: Int, studentId: Int): HttpResponse {
        val request = """{"studentId": "$studentId"}"""
        return sendJson("/groups/$groupId/join", request)
    }

    suspend fun HttpClient.createLesson(name: String, groupIds: List<Int>): HttpResponse {
        val groups = groupIds.joinToString(", ")
        val request = """{"name": "$name", "groupIds": [$groups]}"""
        return sendJson("/lessons", request)
    }

    suspend fun HttpClient.sendJson(url: String, request: String): HttpResponse {
        return post(url) {
            contentType(ContentType.Application.Json)
            setBody(request)
        }
    }

    fun random(prefix: String) = "$prefix #${Random.nextInt(100, 1000)}"

    suspend fun getId(response: HttpResponse) =
        Regex(""""id": (\d+)""").find(response.bodyAsText())
            ?.groups?.lastOrNull()?.value?.toIntOrNull() ?: -1

    companion object {
        const val NON_EXISTENT_ID = 1000
        const val INVALID_ID = "invalid"
    }
}
