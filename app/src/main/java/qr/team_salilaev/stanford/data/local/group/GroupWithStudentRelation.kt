package qr.team_salilaev.stanford.data.local.group

import androidx.room.Embedded
import androidx.room.Relation
import qr.team_salilaev.stanford.data.local.student.StudentEntity

class GroupWithStudentRelation(
    @Embedded
    val groupEntity: GroupEntity,
    @Relation(
        entity = StudentEntity::class,
        parentColumn = "id",
        entityColumn = "group_id"
    )
    val list: List<StudentEntity>,
)