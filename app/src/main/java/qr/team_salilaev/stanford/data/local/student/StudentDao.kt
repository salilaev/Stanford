package qr.team_salilaev.stanford.data.local.student

import androidx.room.Dao
import androidx.room.Query
import qr.team_salilaev.stanford.data.local.base.BaseDao
import qr.team_salilaev.stanford.data.local.group.GroupWithStudentRelation

@Dao
interface StudentDao : BaseDao<StudentEntity> {

    @Query("SELECT * FROM groups WHERE id =:groupId OR :groupId IS NULL LIMIT 1")
    fun getGroupWithStudents(groupId: Int?): GroupWithStudentRelation?
}