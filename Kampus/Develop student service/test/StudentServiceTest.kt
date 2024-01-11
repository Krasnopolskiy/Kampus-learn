import org.hyperskill.hstest.dynamic.DynamicTest
import org.hyperskill.hstest.stage.StageTest
import org.junit.jupiter.api.TestInstance
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import kotlin.test.assertContains
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StudentServiceTest : KampusTest, StageTest<Any>() {
    @DynamicTest
    fun `Student can be created`() = appTest {
        val name = random("Student")
        val response = client.createStudent(name)
        assertContains(
            charSequence = response.bodyAsText(),
            other = name,
            message = "Response does not contain the student name after creation",
        )
    }

    @DynamicTest
    fun `Student can be fetched`() = appTest {
        val name = random("Student")
        val id = getId(client.createStudent(name))
        val response = client.get("/students/$id")
        assertContains(
            charSequence = response.bodyAsText(),
            other = name,
            message = "Fetched student data does not contain the expected name",
        )
    }

    @DynamicTest
    fun `Students can be listed`() = appTest {
        val name = random("Student").also { client.createStudent(it) }
        val response = client.get("/students")
        assertContains(
            charSequence = response.bodyAsText(),
            other = name,
            message = "List of students does not contain the newly created student",
        )
    }

    @DynamicTest
    fun `Group can be created`() = appTest {
        val name = random("Group")
        val response = client.createGroup(name)
        assertContains(
            charSequence = response.bodyAsText(),
            other = name,
            message = "Response does not contain the group name after creation",
        )
    }

    @DynamicTest
    fun `Groups can be listed`() = appTest {
        val name = random("Group").also { client.createGroup(it) }
        val response = client.get("/groups")
        assertContains(
            charSequence = response.bodyAsText(),
            other = name,
            message = "List of groups does not contain the newly created group",
        )
    }

    @DynamicTest
    fun `Student can join group`() = appTest {
        val groupId = getId(client.createGroup(random("Group")))
        val studentId = getId(client.createStudent(random("Student")))
        val response = client.joinGroup(groupId, studentId)
        assertEquals(HttpStatusCode.OK, response.status, "Student failed to join the group, response status is not OK")
    }

    @DynamicTest
    fun `Student can view joined group`() = appTest {
        val groupName = random("Group")
        val groupId = getId(client.createGroup(groupName))
        val studentId = getId(client.createStudent(random("Student")))
        client.joinGroup(groupId, studentId)
        val response = client.get("/students/$studentId")
        assertContains(
            charSequence = response.bodyAsText(),
            other = groupName,
            message = "Student's data does not contain the joined group",
        )
    }
}
