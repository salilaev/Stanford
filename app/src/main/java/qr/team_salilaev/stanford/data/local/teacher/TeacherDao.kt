package qr.team_salilaev.stanford.data.local.teacher

import androidx.room.Dao
import androidx.room.Query
import qr.team_salilaev.stanford.data.local.base.BaseDao

@Dao
interface TeacherDao: BaseDao<TeacherEntity>{

    @Query("SELECT * FROM teachers")
    fun getAllTeacher(): List<TeacherEntity>
}