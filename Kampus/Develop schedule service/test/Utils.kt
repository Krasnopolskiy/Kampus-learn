import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlin.random.Random


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
