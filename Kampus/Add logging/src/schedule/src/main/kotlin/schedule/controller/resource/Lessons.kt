package schedule.controller.resource

import io.ktor.resources.*

@Resource("/lessons")
class Lessons {
    @Resource("{id}")
    class Id(val parent: Lessons = Lessons(), val id: Int)
}