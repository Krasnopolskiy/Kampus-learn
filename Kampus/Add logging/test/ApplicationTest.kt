import org.hyperskill.hstest.dynamic.DynamicTest
import org.hyperskill.hstest.stage.StageTest
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.statement.bodyAsText
import java.util.UUID
import kotlin.test.assertContains

class ApplicationTest : KampusTest, StageTest<Any>() {
    @DynamicTest
    fun `Logs can be fetched`() = appTest {
        val path = UUID.randomUUID().toString()
        val requestId = UUID.randomUUID().toString()
        client.get("/$path") {
            headers { append("Request-Id", requestId) }
        }
        val kek = client.get("/logs")
        val response = kek.bodyAsText()
        assertContains(
            charSequence = response,
            other = path,
            message = "The logs should contain the path used in the request",
        )
        assertContains(
            charSequence = response,
            other = requestId,
            message = "The logs should contain the Request-Id used in the request",
        )
    }
}
