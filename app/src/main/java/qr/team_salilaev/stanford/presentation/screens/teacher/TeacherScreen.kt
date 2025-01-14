package qr.team_salilaev.stanford.presentation.screens.teacher

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import qr.team_salilaev.stanford.R
import qr.team_salilaev.stanford.data.local.course.CourseEntity
import qr.team_salilaev.stanford.data.local.teacher.TeacherEntity
import qr.team_salilaev.stanford.databinding.ScreenTeachersBinding
import qr.team_salilaev.stanford.presentation.screens.courses.add.AddCourseDialog
import qr.team_salilaev.stanford.presentation.screens.courses.edit.EditCourseDialog
import qr.team_salilaev.stanford.presentation.screens.groups.GroupsScreen
import qr.team_salilaev.stanford.presentation.screens.teacher.adapter.TeacherAdapter
import qr.team_salilaev.stanford.presentation.screens.teacher.add.AddTeacherDialog
import qr.team_salilaev.stanford.presentation.screens.teacher.edit.EditTeacherDialog

@AndroidEntryPoint
class TeacherScreen: Fragment(R.layout.screen_teachers){

    private val binding: ScreenTeachersBinding by viewBinding()

    private val teacherAdapter by lazy { TeacherAdapter() }

    private val viewModel : TeacherListViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.rvTeacherList.adapter = teacherAdapter


        teacherAdapter.setTeacherItemDeleteListener { teacher ->
            viewModel.deleteTeacher(teacher)
        }

        teacherAdapter.setTeacherItemEditListener {
            showEditTeacherDialog(it)
        }


        binding.btnAddCourse.setOnClickListener { showAddTeacherDialog() }
        clickButtons()
        setUpObservers()
        viewModel.getTeacherList()
    }

    private fun showEditTeacherDialog(teacherEntity: TeacherEntity) {
        val dialog = EditTeacherDialog(teacherEntity) {
            viewModel.edit(teacherEntity.copy(name = it))
        }
        dialog.show(childFragmentManager, "editTeacher")
    }


    private fun showAddTeacherDialog() {
        val dialog = AddTeacherDialog { teacherName ->
            viewModel.addTeacher(teacherName)
        }
        dialog.show(childFragmentManager, "addTeacher")
    }

    private fun clickButtons() {
        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setUpObservers() {
        viewModel.teacherListLiveData.observe(viewLifecycleOwner) { list ->
           teacherAdapter.setTeacherList(list)
        }
    }
}