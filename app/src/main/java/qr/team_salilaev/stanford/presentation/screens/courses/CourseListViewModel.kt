package qr.team_salilaev.stanford.presentation.screens.courses

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import qr.team_salilaev.stanford.data.local.course.CourseDao
import qr.team_salilaev.stanford.data.local.course.CourseEntity
import qr.team_salilaev.stanford.data.local.course.CourseWithGroupRelation
import javax.inject.Inject

@HiltViewModel
class CourseListViewModel @Inject constructor(
    private val courseDao: CourseDao
) : ViewModel() {

    private val mutableCourseList: MutableLiveData<List<CourseWithGroupRelation>> = MutableLiveData()
    val courseListLiveData: LiveData<List<CourseWithGroupRelation>> get() = mutableCourseList


    fun getCourseList() {
        val courseList = courseDao.getAllCourse()
        mutableCourseList.value = courseList
    }

    fun addCourse(courseName: String) {
        courseDao.insert(CourseEntity(0, courseName))
        getCourseList()
    }

    fun deleteCourse(course: CourseEntity) {
        viewModelScope.launch {
            courseDao.delete(course)
            getCourseList()
        }
    }
    fun edit(course: CourseEntity){
        viewModelScope.launch {
            courseDao.update(course)
            getCourseList()
        }
    }

}