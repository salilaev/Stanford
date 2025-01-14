package qr.team_salilaev.stanford.presentation.screens.courses.add

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import qr.team_salilaev.stanford.R
import qr.team_salilaev.stanford.databinding.DialogAddCourseBinding

class AddCourseDialog(private val onAddClick: (String) -> Unit) : DialogFragment(R.layout.dialog_add_course) {

    private val binding: DialogAddCourseBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnSave.setOnClickListener {
            val courseName = binding.edCourseName.text.toString()
            onAddClick(courseName)
            dismiss()
        }
    }
}