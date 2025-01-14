package qr.team_salilaev.stanford.data.local.group

import androidx.room.Dao
import androidx.room.Query
import qr.team_salilaev.stanford.data.local.base.BaseDao
import qr.team_salilaev.stanford.data.local.course.CourseWithGroupRelation

@Dao
interface GroupDao : BaseDao<GroupEntity> {
    @Query("SELECT * FROM groups WHERE course_id =:courseId OR :courseId IS NULL")
    fun getGroupWithStudents(courseId: Int?): List<GroupWithStudentRelation>
}