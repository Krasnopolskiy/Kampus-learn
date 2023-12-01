package student

import io.ktor.server.application.*
import student.plugin.configureContentNegotiation
import student.plugin.configureKoin
import student.plugin.configureRouting

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

@Suppress("unused")
fun Application.module() {
    configureContentNegotiation()
    configureKoin()
    configureRouting()
}
