package schedule.database.entity

data class Lesson(
    val id: Int,
    val name: String,
    val groupIds: List<Int>
)
