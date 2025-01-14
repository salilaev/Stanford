package qr.team_salilaev.stanford.data.local.group

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "groups")
data class GroupEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "group_name") val groupName: String,
    @ColumnInfo(name = "course_id") val courseId: Int?,
  //  @ColumnInfo(name = "teacher_id") val teacherId: Int
)