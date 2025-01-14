package qr.team_salilaev.stanford.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import qr.team_salilaev.stanford.data.local.AppDatabase
import qr.team_salilaev.stanford.data.local.course.CourseDao
import qr.team_salilaev.stanford.data.local.group.GroupDao
import qr.team_salilaev.stanford.data.local.student.StudentDao
import qr.team_salilaev.stanford.data.local.teacher.TeacherDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @[Provides Singleton]
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase = Room.databaseBuilder(context, AppDatabase::class.java, "learning_centre.db")
        .allowMainThreadQueries()
        .build()

    @[Provides Singleton]
    fun provideCourseDao(appDatabase: AppDatabase): CourseDao = appDatabase.getCourseDao()

    @[Provides Singleton]
    fun provideGroupDao(appDatabase: AppDatabase): GroupDao = appDatabase.getGroupDao()

    @[Provides Singleton]
    fun provideStudentsDao(appDatabase: AppDatabase): StudentDao = appDatabase.getStudentDao()

    @[Provides Singleton]
    fun provideTeachersDao(appDatabase: AppDatabase): TeacherDao = appDatabase.getTeacherDao()

}