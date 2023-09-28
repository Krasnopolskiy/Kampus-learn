import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class Test {
    private val client = HttpClient()

    @Test
    fun `Student service is responding`() = runBlocking {
        val response = client.get("http://localhost:8000")
        assertEquals(HttpStatusCode.OK, response.status)
    }

    @Test
    fun `Student service returns correct string`() = runBlocking {
        val response = client.get("http://localhost:8000")
        assertEquals("Hello from StudentService!", response.bodyAsText())
    }

    @Test
    fun `Schedule service is responding`() = runBlocking {
        val response = client.get("http://localhost:8001")
        assertEquals(HttpStatusCode.OK, response.status)
    }

    @Test
    fun `Schedule service returns correct string`() = runBlocking {
        val response = client.get("http://localhost:8001")
        assertEquals("Hello from ScheduleService!", response.bodyAsText())
    }
}
