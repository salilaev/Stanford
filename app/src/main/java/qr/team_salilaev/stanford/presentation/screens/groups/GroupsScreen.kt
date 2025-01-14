package qr.team_salilaev.stanford.presentation.screens.groups

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import qr.team_salilaev.stanford.R
import qr.team_salilaev.stanford.data.local.course.CourseEntity
import qr.team_salilaev.stanford.data.local.group.GroupEntity
import qr.team_salilaev.stanford.databinding.ScreenGroupsBinding
import qr.team_salilaev.stanford.presentation.screens.courses.edit.EditCourseDialog
import qr.team_salilaev.stanford.presentation.screens.groups.adapter.GroupAdapter
import qr.team_salilaev.stanford.presentation.screens.groups.add.AddGroupDialog
import qr.team_salilaev.stanford.presentation.screens.groups.edit.EditGroupDialog
import qr.team_salilaev.stanford.presentation.screens.students.StudentsScreen

@AndroidEntryPoint
class GroupsScreen : Fragment(R.layout.screen_groups) {

    private val binding: ScreenGroupsBinding by viewBinding()

    private val groupAdapter by lazy { GroupAdapter() }

    private val viewModel: GroupListViewModel by viewModels()

    private var courseId: Int? = null

    private var courseName: String = "Groups"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let {
            courseId = it.getInt(COURSE_ID)
            courseName = it.getString(COURSE_NAME).toString()
        }

        groupAdapter.setGroupItemListener {
            val bundle  = Bundle()
            bundle.putInt(StudentsScreen.GROUP_ID_KEY, it.groupEntity.id)
            bundle.putString(StudentsScreen.GROUP_NAME_KEY, it.groupEntity.groupName)
            findNavController().navigate(R.id.action_groupsScreen_to_studentsScreen,bundle)
        }

        binding.tvTitle.text = courseName
        viewModel.getGroupList(courseId)
        binding.rvContactList.adapter = groupAdapter


        binding.btnAddGroup.setOnClickListener { showAddGroupDialog() }
        clickButtons()
        setUpObservers()

        groupAdapter.setGroupItemDeleteListener { relation ->
            viewModel.deleteGroup(relation.groupEntity)
        }

        groupAdapter.setGroupEditItemListener {
            showEditGroupDialog(it.groupEntity)
        }

        viewModel.getGroupList()

    }

    private fun showEditGroupDialog(groupEntity: GroupEntity) {
        val dialog = EditGroupDialog(groupEntity) {
            viewModel.edit(groupEntity.copy(groupName = it))
        }

        dialog.show(childFragmentManager, "editGroup")
    }

    private fun showAddGroupDialog() {
        val dialog = AddGroupDialog { groupName ->
            viewModel.addGroup(groupName)
        }
        dialog.show(childFragmentManager, "addGroup")
    }

    private fun clickButtons() {
        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setUpObservers() {
        viewModel.groupListLiveData.observe(viewLifecycleOwner) { list ->
            groupAdapter.setGroupList(list)
        }
    }

    companion object {
        const val COURSE_ID = "course_id"
        const val  COURSE_NAME = "course_name"
    }
}

//list
//add
//edit
//delete
