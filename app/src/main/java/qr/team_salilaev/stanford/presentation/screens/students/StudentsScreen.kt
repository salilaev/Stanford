package qr.team_salilaev.stanford.presentation.screens.students

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import qr.team_salilaev.stanford.R
import qr.team_salilaev.stanford.data.local.student.StudentEntity
import qr.team_salilaev.stanford.databinding.ScreenStudentsBinding
import qr.team_salilaev.stanford.presentation.screens.students.adapter.StudentAdapter
import qr.team_salilaev.stanford.presentation.screens.students.add.AddStudentDialog
import qr.team_salilaev.stanford.presentation.screens.students.edit.EditStudentDialog

@AndroidEntryPoint
class StudentsScreen : Fragment(R.layout.screen_students) {

    private val binding: ScreenStudentsBinding by viewBinding()

    private val studentAdapter by lazy { StudentAdapter() }

    private val viewModel: StudentViewModel by viewModels()

    private var groupId: Int? = null

    private var groupName: String = "Students"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        arguments?.let {
            groupId = it.getInt(GROUP_ID_KEY)
            groupName = it.getString(GROUP_NAME_KEY).toString()
        }

        binding.tvTitle.text = groupName

        binding.rvStudentList.adapter = studentAdapter

        studentAdapter.setStudentItemDeleteListener { student ->
            viewModel.deleteStudent(student)
        }

        studentAdapter.setStudentEditItemListener {
            showEditStudentDialog(it)
        }

        clickButtons()
        setUpObservers()
        viewModel.getStudentList(groupId)
    }


    private fun showAddStudentDialog() {
        val dialog = AddStudentDialog { studentName, studentAge, studentLocation ->
            viewModel.addStudent(studentName, studentAge, studentLocation, groupId)
        }
        dialog.show(childFragmentManager, "addStudent")
    }


    private fun showEditStudentDialog(studentEntity: StudentEntity) {
        val dialog = EditStudentDialog(studentEntity) { sureName, age, location ->
            viewModel.edit(studentEntity.copy(studentName = sureName, studentAge = age, studentLocation = location))
        }
        dialog.show(childFragmentManager, "editGroup")
    }

    private fun clickButtons() {
        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.idAdd.setOnClickListener { showAddStudentDialog() }
    }

    private fun setUpObservers() {
        viewModel.studentListLiveData.observe(viewLifecycleOwner) { list ->
            studentAdapter.setStudentList(list)
        }
    }

    companion object {
        const val GROUP_ID_KEY = "group_id"
        const val GROUP_NAME_KEY = "group_name"
    }
}