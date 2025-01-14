package qr.team_salilaev.stanford.presentation.screens.students

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import qr.team_salilaev.stanford.data.local.student.StudentDao
import qr.team_salilaev.stanford.data.local.student.StudentEntity
import javax.inject.Inject

@HiltViewModel
class StudentViewModel @Inject constructor(
    private val studentDao: StudentDao,
) : ViewModel() {

    private val mutableStudentList: MutableLiveData<List<StudentEntity>> = MutableLiveData()
    val studentListLiveData: LiveData<List<StudentEntity>> get() = mutableStudentList

    private var groupId: Int? = null

    fun getStudentList(groupId: Int? = this.groupId) {
        this.groupId = groupId
        viewModelScope.launch {
            val groupWithStudent = studentDao.getGroupWithStudents(groupId)
            mutableStudentList.value = groupWithStudent?.list?: emptyList()
        }
    }

    fun addStudent(studentName: String, studentAge: String, studentLocation: String, groupId: Int?) {
        studentDao.insert(StudentEntity(0, studentName, studentAge, studentLocation, groupId))
        getStudentList(groupId)
    }

    fun edit(student: StudentEntity){
        viewModelScope.launch {
            studentDao.update(student)
            getStudentList()
        }
    }

    fun deleteStudent(student: StudentEntity){
        viewModelScope.launch {
            studentDao.delete(student)
            getStudentList()
        }
    }

}