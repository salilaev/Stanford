package qr.team_salilaev.stanford.data.local.student

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "students")
data class StudentEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "student_name") val studentName: String,
    @ColumnInfo(name = "student_age") val studentAge: String,
    @ColumnInfo(name = "student_location") val studentLocation: String,
    @ColumnInfo(name = "group_id") val groupId: Int?
)