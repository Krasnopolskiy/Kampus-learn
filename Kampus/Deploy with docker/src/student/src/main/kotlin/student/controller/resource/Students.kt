package student.controller.resource

import io.ktor.resources.*

@Resource("/students")
class Students {
    @Resource("{id}")
    class Id(val parent: Students = Students(), val id: Int)
}
