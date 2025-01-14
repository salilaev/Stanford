package qr.team_salilaev.stanford.presentation.screens.teacher

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import qr.team_salilaev.stanford.data.local.course.CourseEntity
import qr.team_salilaev.stanford.data.local.teacher.TeacherDao
import qr.team_salilaev.stanford.data.local.teacher.TeacherEntity
import javax.inject.Inject


@HiltViewModel
class TeacherListViewModel @Inject constructor(
    private val teacherDao : TeacherDao
) :ViewModel() {

    private val mutableTeacherList: MutableLiveData<List<TeacherEntity>> = MutableLiveData()
    val teacherListLiveData: LiveData<List<TeacherEntity>> get() = mutableTeacherList

    fun getTeacherList(){
        val teacherList = teacherDao.getAllTeacher()
        mutableTeacherList.value = teacherList
    }

    fun addTeacher(teacherName: String){
        teacherDao.insert(TeacherEntity(0,teacherName))
        getTeacherList()
    }

    fun deleteTeacher(teacher: TeacherEntity){
        viewModelScope.launch {
            teacherDao.delete(teacher)
            getTeacherList()
        }
    }

    fun edit(teacher: TeacherEntity){
        viewModelScope.launch {
            teacherDao.update(teacher)
            getTeacherList()
        }
    }

}