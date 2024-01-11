import org.hyperskill.hstest.dynamic.DynamicTest
import org.hyperskill.hstest.stage.StageTest
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import kotlin.test.assertContains
import kotlin.test.assertEquals
import KampusTest.Companion.INVALID_ID
import KampusTest.Companion.NON_EXISTENT_ID

class ScheduleServiceTest : KampusTest, StageTest<Any>() {
    @DynamicTest
    fun `Lesson can be created`() = appTest {
        val name = random("Lesson")
        val response = client.createLesson(name, emptyList())
        assertContains(
            charSequence = response.bodyAsText(),
            other = name,
            message = "Response does not contain the lesson name after creation",
        )
    }

    @DynamicTest
    fun `Lesson with group can be created`() = appTest {
        val groupName = random("Group")
        val groupId = getId(client.createGroup(groupName))
        val response = client.createLesson(random("Lesson"), listOf(groupId))
        assertContains(
            charSequence = response.bodyAsText(),
            other = groupName,
            message = "Response does not contain the group name when creating a lesson with a group",
        )
    }

    @DynamicTest
    fun `Lesson with group can be fetched`() = appTest {
        val groupName = random("Group")
        val groupId = getId(client.createGroup(groupName))
        val lessonId = getId(client.createLesson(random("Lesson"), listOf(groupId)))
        val response = client.get("/lessons/$lessonId")
        assertContains(
            charSequence = response.bodyAsText(),
            other = groupName,
            message = "Fetched lesson data does not contain the group name",
        )
    }

    @DynamicTest
    fun `Lesson with empty name cannot be created`() = appTest {
        val request = """{"name": "", "groupIds": []}"""
        val response = client.sendJson("/lessons", request)
        assertEquals(
            expected = HttpStatusCode.BadRequest,
            actual = response.status,
            message = "Lesson creation should fail with BadRequest for empty name",
        )
        assertContains(
            charSequence = response.bodyAsText(),
            other = "Lesson name cannot be empty",
            message = "Response should indicate that lesson name cannot be empty",
        )
    }

    @DynamicTest
    fun `Non-existent lesson cannot be fetched`() = appTest {
        val response = client.get("/lessons/$NON_EXISTENT_ID")
        assertEquals(
            expected = HttpStatusCode.NotFound,
            actual = response.status,
            message = "Fetching a non-existent lesson should return 'NotFound' status",
        )
    }

    @DynamicTest
    fun `Lesson with invalid id cannot be fetched`() = appTest {
        val response = client.get("/lessons/$INVALID_ID")
        assertEquals(
            expected = HttpStatusCode.BadRequest,
            actual = response.status,
            message = "Fetching a lesson with invalid ID should return 'BadRequest' status",
        )
    }
}
