package kampus.plugin

import org.koin.ktor.plugin.Koin
import io.ktor.server.application.Application
import io.ktor.server.application.install
import kampus.config.KoinConfig

fun Application.configureKoin() {
    install(Koin) {
        modules(KoinConfig.mappers, KoinConfig.services, KoinConfig.handlers)
    }
}
