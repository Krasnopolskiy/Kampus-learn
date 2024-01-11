package kampus.config

import org.koin.dsl.module
import kampus.controller.handler.GroupHandler
import kampus.controller.handler.LessonHandler
import kampus.controller.handler.StudentHandler
import kampus.controller.handler.impl.GroupHandlerImpl
import kampus.controller.handler.impl.LessonHandlerImpl
import kampus.controller.handler.impl.StudentHandlerImpl
import kampus.model.mapper.GroupMapper
import kampus.model.mapper.LessonMapper
import kampus.model.mapper.StudentMapper
import kampus.service.GroupService
import kampus.service.LessonService
import kampus.service.StudentService
import kampus.service.impl.GroupServiceImpl
import kampus.service.impl.LessonServiceImpl
import kampus.service.impl.StudentServiceImpl

object KoinConfig {
    val mappers = module {
        single<StudentMapper> { StudentMapper() }
        single<GroupMapper> { GroupMapper() }
        single<LessonMapper> { LessonMapper() }
    }
    val services = module {
        single<StudentService> { StudentServiceImpl() }
        single<GroupService> { GroupServiceImpl() }
        single<LessonService> { LessonServiceImpl() }
    }
    val handlers = module {
        single<StudentHandler> { StudentHandlerImpl() }
        single<GroupHandler> { GroupHandlerImpl() }
        single<LessonHandler> { LessonHandlerImpl() }
    }
}
