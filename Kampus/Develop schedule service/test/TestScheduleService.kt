import AbstractTest.Companion.BASE_URL
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.jupiter.api.TestInstance
import kotlin.test.assertContains


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestScheduleService : AbstractTest {
    private val scheduleUrl = "$BASE_URL:8001"
    override val studentUrl = "$BASE_URL:8000"
    override val client = HttpClient()


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
        val response = client.get("$scheduleUrl/lessons/$lessonId")
        assertContains(response.bodyAsText(), groupName)
    }

    private suspend fun createLesson(name: String, groupIds: List<Int>): HttpResponse {
        val groups = groupIds.joinToString(", ")
        val request = """{"name": "$name", "groupIds": [$groups]}"""
        return client.sendJson("$scheduleUrl/lessons", request)
    }
}
