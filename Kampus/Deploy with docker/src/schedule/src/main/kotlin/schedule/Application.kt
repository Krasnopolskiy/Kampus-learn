package schedule

import io.ktor.server.application.*
import schedule.plugin.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

@Suppress("unused")
fun Application.module() {
    configureContentNegotiation()
    configureRequestValidation()
    configureKoin()
    configureResources()
    configureRouting()
    configureStatusPages()
    configureCallLogging()
}
