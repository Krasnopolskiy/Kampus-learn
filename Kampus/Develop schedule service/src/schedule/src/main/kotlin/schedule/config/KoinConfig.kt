package schedule.config

import org.koin.dsl.module
import schedule.controller.handler.LessonHandler
import schedule.controller.handler.impl.LessonHandlerImpl
import schedule.model.mapper.LessonMapper
import schedule.service.LessonService
import schedule.service.impl.LessonServiceImpl

object KoinConfig {
    val mappers = module {
        single<LessonMapper> { LessonMapper() }
    }
    val services = module {
        single<LessonService> { LessonServiceImpl() }
    }
    val handlers = module {
        single<LessonHandler> { LessonHandlerImpl() }
    }
}
