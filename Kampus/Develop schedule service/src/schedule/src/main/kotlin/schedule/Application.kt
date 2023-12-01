package schedule

import io.ktor.server.application.*
import schedule.plugin.configureContentNegotiation
import schedule.plugin.configureKoin
import schedule.plugin.configureRouting

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureContentNegotiation()
    configureKoin()
    configureRouting()
}
