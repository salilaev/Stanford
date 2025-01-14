package qr.team_salilaev.stanford.presentation.screens.courses

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import qr.team_salilaev.stanford.R
import qr.team_salilaev.stanford.data.local.course.CourseEntity
import qr.team_salilaev.stanford.databinding.ScreenCoursesBinding
import qr.team_salilaev.stanford.presentation.screens.courses.adapter.CourseAdapter
import qr.team_salilaev.stanford.presentation.screens.courses.add.AddCourseDialog
import qr.team_salilaev.stanford.presentation.screens.courses.edit.EditCourseDialog
import qr.team_salilaev.stanford.presentation.screens.groups.GroupsScreen

@AndroidEntryPoint
class CoursesScreen : Fragment(R.layout.screen_courses) {

    private val binding: ScreenCoursesBinding by viewBinding()

    private val coursesAdapter by lazy { CourseAdapter() }

    private val viewModel: CourseListViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.rvCourseList.adapter = coursesAdapter
        coursesAdapter.setCourseItemClickListener {
            val bundle = Bundle()
            bundle.putInt(GroupsScreen.COURSE_ID, it.courseEntity.id)
            bundle.putString(GroupsScreen.COURSE_NAME,it.courseEntity.courseName)
            findNavController().navigate(R.id.action_coursesScreen_to_groupsScreen, bundle)
        }


        coursesAdapter.setCourseItemDeleteListener { relation ->
            viewModel.deleteCourse(relation.courseEntity)
        }

        coursesAdapter.setCourseItemEditListener {
            showEditCourseDialog(it.courseEntity)
        }


        binding.btnAddCourse.setOnClickListener { showAddCourseDialog() }
        clickButtons()
        setUpObservers()
        viewModel.getCourseList()
    }

    private fun showEditCourseDialog(courseEntity: CourseEntity) {
        val dialog = EditCourseDialog(courseEntity) {
            viewModel.edit(courseEntity.copy(courseName = it))
        }

        dialog.show(childFragmentManager, "editCourse")
    }


    private fun showAddCourseDialog() {
        val dialog = AddCourseDialog { courseName ->
            viewModel.addCourse(courseName)
        }
        dialog.show(childFragmentManager, "addCourse")
    }

    private fun clickButtons() {
        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setUpObservers() {
        viewModel.courseListLiveData.observe(viewLifecycleOwner) { list ->
            coursesAdapter.setCourseList(list)
        }
    }
}

