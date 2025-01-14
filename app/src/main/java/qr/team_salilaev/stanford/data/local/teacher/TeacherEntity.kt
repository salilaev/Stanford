package qr.team_salilaev.stanford.data.local.teacher

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import qr.team_salilaev.stanford.data.local.student.StudentEntity

@Entity(tableName = "teachers")
data class TeacherEntity (
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "teacher_name") val name: String
)