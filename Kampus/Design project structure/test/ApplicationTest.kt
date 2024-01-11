import org.hyperskill.hstest.dynamic.DynamicTest
import org.hyperskill.hstest.stage.StageTest
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import kotlin.test.assertEquals

class ApplicationTest : KampusTest, StageTest<Any>() {
    @DynamicTest
    fun `Student service is responding`() = appTest {
        val response = client.get("/students")
        assertEquals(HttpStatusCode.OK, response.status, "Student service did not respond with OK status")
    }

    @DynamicTest
    fun `Schedule service is responding`() = appTest {
        val response = client.get("/schedule")
        assertEquals(HttpStatusCode.OK, response.status, "Schedule service did not respond with OK status")
    }
}
