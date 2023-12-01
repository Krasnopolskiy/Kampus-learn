package student.plugin

import io.ktor.server.application.*
import io.ktor.server.resources.*

fun Application.installResources() {
    install(Resources)
}
