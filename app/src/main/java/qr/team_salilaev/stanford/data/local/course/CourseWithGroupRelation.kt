package qr.team_salilaev.stanford.data.local.course

import androidx.room.Embedded
import androidx.room.Relation
import qr.team_salilaev.stanford.data.local.group.GroupEntity
import qr.team_salilaev.stanford.data.local.group.GroupWithStudentRelation

data class CourseWithGroupRelation(
    @Embedded
    val courseEntity: CourseEntity,
    @Relation(
        entity = GroupEntity::class,
        parentColumn = "id",
        entityColumn = "course_id"
    )
    val list: List<GroupWithStudentRelation>,
)

//one to one
//one to many
//many to many
//course -> List<Group>

//Group-> List<Student>
