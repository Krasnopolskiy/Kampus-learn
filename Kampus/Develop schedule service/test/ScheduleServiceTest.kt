import org.hyperskill.hstest.dynamic.DynamicTest
import org.hyperskill.hstest.stage.StageTest
import org.junit.jupiter.api.TestInstance
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlin.test.assertContains

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
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
}
