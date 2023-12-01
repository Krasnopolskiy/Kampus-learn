package schedule.plugin

import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.plugins.callloging.*
import io.ktor.server.request.*
import org.slf4j.MDC
import org.slf4j.event.Level
import java.util.*

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
