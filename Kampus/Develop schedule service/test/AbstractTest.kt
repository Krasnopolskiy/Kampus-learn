import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlin.random.Random

interface AbstractTest {

    val studentUrl: String
    val client: HttpClient

    suspend fun createGroup(name: String): HttpResponse {
        val request = """{"name": "$name"}"""
        return client.sendJson("$studentUrl/groups", request)
    }

    suspend fun HttpClient.sendJson(url: String, request: String): HttpResponse {
        return post(url) {
            contentType(ContentType.Application.Json)
            setBody(request)
        }
    }

    fun random(prefix: String) = "$prefix #${Random.nextInt(100, 1000)}"

    suspend fun getId(response: HttpResponse) =
        Regex(""""id": (\d+)""").find(response.bodyAsText())
            ?.groups?.lastOrNull()?.value?.toIntOrNull() ?: -1

    companion object {
        const val BASE_URL = "http://localhost"
    }
}
