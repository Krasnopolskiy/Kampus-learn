package kampus

import io.ktor.server.application.Application
import kampus.plugin.*

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
}
