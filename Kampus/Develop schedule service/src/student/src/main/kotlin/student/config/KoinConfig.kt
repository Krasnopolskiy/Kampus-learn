package student.config

import org.koin.dsl.module
import student.controller.handler.GroupHandler
import student.controller.handler.StudentHandler
import student.controller.handler.impl.GroupHandlerImpl
import student.controller.handler.impl.StudentHandlerImpl
import student.model.mapper.GroupMapper
import student.model.mapper.StudentMapper
import student.service.GroupService
import student.service.StudentService
import student.service.impl.GroupServiceImpl
import student.service.impl.StudentServiceImpl

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
