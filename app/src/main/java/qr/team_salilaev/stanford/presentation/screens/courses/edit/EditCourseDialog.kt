package qr.team_salilaev.stanford.presentation.screens.courses.edit

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import qr.team_salilaev.stanford.R
import qr.team_salilaev.stanford.data.local.course.CourseEntity
import qr.team_salilaev.stanford.databinding.DialogEditCourseBinding

class EditCourseDialog(private val courseEntity: CourseEntity, private val onEditClick: (String) -> Unit) : DialogFragment(R.layout.dialog_edit_course) {

    private val binding: DialogEditCourseBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.edCourseName.setText(courseEntity.courseName)
        binding.btnEdit.setOnClickListener {
            val courseName = binding.edCourseName.text.toString()
            onEditClick.invoke(courseName)
            dismiss()
        }
    }
}