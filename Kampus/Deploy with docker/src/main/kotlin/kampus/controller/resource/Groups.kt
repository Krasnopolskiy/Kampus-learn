package kampus.controller.resource

import io.ktor.resources.Resource

@Resource("/groups")
class Groups {
    @Resource("{id}/join")
    class Join(val parent: Groups = Groups(), val id: Int)
}
