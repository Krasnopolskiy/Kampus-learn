package student

import io.ktor.server.application.*
import student.plugin.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

@Suppress("unused")
fun Application.module() {
    configureContentNegotiation()
    configureRequestValidation()
    configureKoin()
    installResources()
    configureRouting()
    configureStatusPages()
}
