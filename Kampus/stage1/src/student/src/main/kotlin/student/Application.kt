package student

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    routing {
        get("/") {
            call.respondText("Hello from StudentService!")
        }
    }
}