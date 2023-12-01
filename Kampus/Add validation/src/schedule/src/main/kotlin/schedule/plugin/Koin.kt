package schedule.plugin

import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin
import schedule.config.KoinConfig

fun Application.configureKoin() {
    install(Koin) {
        modules(KoinConfig.mappers, KoinConfig.services, KoinConfig.handlers)
    }
}
