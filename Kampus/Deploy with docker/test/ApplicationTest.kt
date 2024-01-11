import org.hyperskill.hstest.dynamic.DynamicTest
import org.hyperskill.hstest.stage.StageTest
import org.junit.AfterClass
import org.junit.BeforeClass
import org.testcontainers.containers.GenericContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.statement.bodyAsText
import java.util.UUID
import kotlin.test.assertContains

@Testcontainers
class ApplicationTest : KampusTest, StageTest<Any>() {
    private val url = "http://localhost:${container.getMappedPort(80)}"

    @DynamicTest
    fun `Logs can be fetched`() = appTest {
        val path = UUID.randomUUID().toString()
        val requestId = UUID.randomUUID().toString()
        client.get("$url/$path") {
            headers { append("Request-Id", requestId) }
        }
        val kek = client.get("$url/logs")
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

    companion object {
        @JvmStatic
        @Container
        val container = GenericContainer("kampus-service:1.0.0")
            .also { it.setPortBindings(listOf("80:80")) }

        @JvmStatic
        @BeforeClass
        fun startContainers() = container.start()

        @JvmStatic
        @AfterClass
        fun stopContainers() = container.stop()
    }
}
