package kampus

import io.ktor.server.application.Application
import kampus.plugin.configureContentNegotiation
import kampus.plugin.configureKoin
import kampus.plugin.configureRouting

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

@Suppress("unused")
fun Application.module() {
    configureContentNegotiation()
    configureKoin()
    configureRouting()
}
