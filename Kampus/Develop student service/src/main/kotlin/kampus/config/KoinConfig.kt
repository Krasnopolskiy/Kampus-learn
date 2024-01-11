package kampus.config

import org.koin.dsl.module
import kampus.controller.handler.GroupHandler
import kampus.controller.handler.StudentHandler
import kampus.controller.handler.impl.GroupHandlerImpl
import kampus.controller.handler.impl.StudentHandlerImpl
import kampus.model.mapper.GroupMapper
import kampus.model.mapper.StudentMapper
import kampus.service.GroupService
import kampus.service.StudentService
import kampus.service.impl.GroupServiceImpl
import kampus.service.impl.StudentServiceImpl

object KoinConfig {
    val mappers = module {
        single<StudentMapper> { StudentMapper() }
        single<GroupMapper> { GroupMapper() }
    }
    val services = module {
        single<StudentService> { StudentServiceImpl() }
        single<GroupService> { GroupServiceImpl() }
    }
    val handlers = module {
        single<StudentHandler> { StudentHandlerImpl() }
        single<GroupHandler> { GroupHandlerImpl() }
    }
}
