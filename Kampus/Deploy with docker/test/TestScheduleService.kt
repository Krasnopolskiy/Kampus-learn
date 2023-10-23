import AbstractTest.Companion.BASE_URL
import AbstractTest.Companion.INVALID_ID
import AbstractTest.Companion.NON_EXISTENT_ID
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test
import org.junit.jupiter.api.TestInstance
import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.Network
import org.testcontainers.junit.jupiter.Testcontainers
import java.util.*
import kotlin.test.assertContains
import kotlin.test.assertEquals


@Testcontainers
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestScheduleService : AbstractTest {
    private val scheduleUrl = "$BASE_URL:${scheduleContainer.getMappedPort(8001)}"
    override val studentUrl = "$BASE_URL:${studentContainer.getMappedPort(8000)}"
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

    @Test
    fun `Lesson with empty name cannot be created`() = runBlocking {
        val request = """{"name": "", "groupIds": []}"""
        val response = client.sendJson("$scheduleUrl/lessons", request)
        assertEquals(HttpStatusCode.BadRequest, response.status)
        assertContains(response.bodyAsText(), "Lesson name cannot be empty")
    }

    @Test
    fun `Non-existent lesson cannot be fetched`() = runBlocking {
        val response = client.get("$scheduleUrl/lessons/$NON_EXISTENT_ID")
        assertEquals(HttpStatusCode.NotFound, response.status)
    }

    @Test
    fun `Lesson with invalid id cannot be fetched`() = runBlocking {
        val response = client.get("$scheduleUrl/lessons/$INVALID_ID")
        assertEquals(HttpStatusCode.BadRequest, response.status)
    }

    @Test
    fun `Logs can be fetched`() = runBlocking {
        val path = UUID.randomUUID().toString()
        val requestId = UUID.randomUUID().toString()
        client.get("$scheduleUrl/$path") {
            headers { append("Request-Id", requestId) }
        }
        val response = client.get("$scheduleUrl/logs").bodyAsText()
        assertContains(response, path)
        assertContains(response, requestId)
    }

    private suspend fun createLesson(name: String, groupIds: List<Int>): HttpResponse {
        val groups = groupIds.joinToString(", ")
        val request = """{"name": "$name", "groupIds": [$groups]}"""
        return client.sendJson("$scheduleUrl/lessons", request)
    }

    companion object {
        private val network = Network.newNetwork()

        private val studentContainer =
            GenericContainer("kampus-student-service:1.0.0")
                .withExposedPorts(8000)
                .withNetwork(network)
                .withNetworkAliases("student")

        private val scheduleContainer =
            GenericContainer("kampus-schedule-service:1.0.0")
                .withExposedPorts(8001)
                .withNetwork(network)

        @JvmStatic
        @BeforeClass
        fun startContainers() {
            studentContainer.start()
            scheduleContainer.withEnv("STUDENT_HOST", "student").start()
        }

        @JvmStatic
        @AfterClass
        fun stopContainers() {
            studentContainer.stop()
            scheduleContainer.stop()
        }
    }
}
