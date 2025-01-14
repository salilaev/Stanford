package qr.team_salilaev.stanford.presentation.screens.groups

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import qr.team_salilaev.stanford.data.local.group.GroupDao
import qr.team_salilaev.stanford.data.local.group.GroupEntity
import qr.team_salilaev.stanford.data.local.group.GroupWithStudentRelation
import javax.inject.Inject

@HiltViewModel
class GroupListViewModel @Inject constructor(
    private val groupDao: GroupDao,
) : ViewModel() {

    private val mutableGroupList: MutableLiveData<List<GroupWithStudentRelation>> = MutableLiveData()
    val groupListLiveData: LiveData<List<GroupWithStudentRelation>> get() = mutableGroupList

    private var courseId: Int? = null


    fun getGroupList(courseId: Int? = this.courseId) {
        this.courseId = courseId
        viewModelScope.launch{
            val groupList = groupDao.getGroupWithStudents(courseId)
            mutableGroupList.value = groupList
        }
    }

    fun addGroup(groupName: String) {
        groupDao.insert(GroupEntity(0, groupName, courseId))
        getGroupList(courseId)
    }

    fun deleteGroup(group: GroupEntity){
        viewModelScope.launch {
            groupDao.delete(group)
            getGroupList()
        }
    }

    fun edit(group: GroupEntity){
        viewModelScope.launch {
            groupDao.update(group)
            getGroupList()
        }
    }

}