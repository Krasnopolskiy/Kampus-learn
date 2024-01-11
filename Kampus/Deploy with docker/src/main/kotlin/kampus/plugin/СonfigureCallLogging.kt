package kampus.plugin

import org.slf4j.MDC
import org.slf4j.event.Level
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.callloging.CallLogging
import io.ktor.server.plugins.origin
import io.ktor.server.request.header
import io.ktor.server.request.path
import java.util.UUID

fun Application.configureCallLogging() {
    install(CallLogging) {
        level = Level.INFO
        mdc("requestId") { call -> call.request.header("Request-Id") ?: UUID.randomUUID().toString() }
        format { call ->
            val path = call.request.path()
            val requestId = MDC.get("requestId")
            val remote = call.request.origin.remoteHost
            "Request [$requestId] from [$remote] to the path: [$path]"
        }
    }
}
