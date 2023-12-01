package student.plugin

import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin
import student.config.KoinConfig

fun Application.configureKoin() {
    install(Koin) {
        modules(KoinConfig.mappers, KoinConfig.services, KoinConfig.handlers)
    }
}
