package qr.team_salilaev.stanford.data.local.course

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "courses")
data class CourseEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "course_name") val courseName: String
)
