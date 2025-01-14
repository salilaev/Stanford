package qr.team_salilaev.stanford.data.local.course

import androidx.room.Dao
import androidx.room.Query
import qr.team_salilaev.stanford.data.local.base.BaseDao

@Dao
interface CourseDao : BaseDao<CourseEntity> {

    @Query("SELECT * FROM courses")
    fun getAllCourse(): List<CourseWithGroupRelation>
}