package qr.team_salilaev.stanford.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import qr.team_salilaev.stanford.data.local.course.CourseDao
import qr.team_salilaev.stanford.data.local.course.CourseEntity
import qr.team_salilaev.stanford.data.local.group.GroupDao
import qr.team_salilaev.stanford.data.local.group.GroupEntity
import qr.team_salilaev.stanford.data.local.student.StudentDao
import qr.team_salilaev.stanford.data.local.student.StudentEntity
import qr.team_salilaev.stanford.data.local.teacher.TeacherDao
import qr.team_salilaev.stanford.data.local.teacher.TeacherEntity

@Database(entities = [CourseEntity::class, GroupEntity::class, StudentEntity::class, TeacherEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getCourseDao(): CourseDao

    abstract fun getGroupDao(): GroupDao

    abstract fun getStudentDao(): StudentDao

    abstract fun getTeacherDao(): TeacherDao

}