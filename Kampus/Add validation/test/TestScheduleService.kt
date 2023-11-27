import AbstractTest.Companion.BASE_URL
import AbstractTest.Companion.INVALID_ID
import AbstractTest.Companion.NON_EXISTENT_ID
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
class TestScheduleService : AbstractTest, StageTest<Any>() {
    private val scheduleUrl = "$BASE_URL:8001"
    override val studentUrl = "$BASE_URL:8000"
    override val client = HttpClient()

    @DynamicTest
    fun `Lesson can be created`(): CheckResult {
        var result: CheckResult = CheckResult.correct()
        runBlocking {
            try {
                val name = random("Lesson")
                val response = createLesson(name, emptyList())
                assertContains(response.bodyAsText(), name)
            } catch (exc: Throwable) {
                result = CheckResult.wrong(exc.message)
            }
        }
        return result
    }

    @DynamicTest
    fun `Lesson with group can be created`(): CheckResult {
        var result: CheckResult = CheckResult.correct()
        runBlocking {
            try {
                val groupName = random("Group")
                val groupId = getId(createGroup(groupName))
                val response = createLesson(random("Lesson"), listOf(groupId))
                assertContains(response.bodyAsText(), groupName)
            } catch (exc: Throwable) {
                result = CheckResult.wrong(exc.message)
            }
        }
        return result
    }

    @DynamicTest
    fun `Lesson with group can be fetched`(): CheckResult {
        var result: CheckResult = CheckResult.correct()
        runBlocking {
            try {
                val groupName = random("Group")
                val groupId = getId(createGroup(groupName))
                val lessonId = getId(createLesson(random("Lesson"), listOf(groupId)))
                val response = client.get("$scheduleUrl/lessons/$lessonId")
                assertContains(response.bodyAsText(), groupName)
            } catch (exc: Throwable) {
                result = CheckResult.wrong(exc.message)
            }
        }
        return result
    }

    @DynamicTest
    fun `Lesson with empty name cannot be created`(): CheckResult {
        var result: CheckResult = CheckResult.correct()
        runBlocking {
            try {
                val request = """{"name": "", "groupIds": []}"""
                val response = client.sendJson("$scheduleUrl/lessons", request)
                assertEquals(HttpStatusCode.BadRequest, response.status)
                assertContains(response.bodyAsText(), "Lesson name cannot be empty")
            } catch (exc: Throwable) {
                result = CheckResult.wrong(exc.message)
            }
        }
        return result
    }

    @DynamicTest
    fun `Non-existent lesson cannot be fetched`(): CheckResult {
        var result: CheckResult = CheckResult.correct()
        runBlocking {
            try {
                val response = client.get("$scheduleUrl/lessons/$NON_EXISTENT_ID")
                assertEquals(HttpStatusCode.NotFound, response.status)
            } catch (exc: Throwable) {
                result = CheckResult.wrong(exc.message)
            }
        }
        return result
    }

    @DynamicTest
    fun `Lesson with invalid id cannot be fetched`(): CheckResult {
        var result: CheckResult = CheckResult.correct()
        runBlocking {
            try {
                val response = client.get("$scheduleUrl/lessons/$INVALID_ID")
                assertEquals(HttpStatusCode.BadRequest, response.status)
            } catch (exc: Throwable) {
                result = CheckResult.wrong(exc.message)
            }
        }
        return result
    }

    private suspend fun createLesson(name: String, groupIds: List<Int>): HttpResponse {
        val groups = groupIds.joinToString(", ")
        val request = """{"name": "$name", "groupIds": [$groups]}"""
        return client.sendJson("$scheduleUrl/lessons", request)
    }
}
