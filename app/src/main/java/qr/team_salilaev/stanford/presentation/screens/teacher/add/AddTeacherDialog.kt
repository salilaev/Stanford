package qr.team_salilaev.stanford.presentation.screens.teacher.add

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import qr.team_salilaev.stanford.R
import qr.team_salilaev.stanford.databinding.DialogAddCourseBinding
import qr.team_salilaev.stanford.databinding.DialogAddTeacherBinding

class AddTeacherDialog(private val onAddClick:(String)->Unit): DialogFragment(R.layout.dialog_add_teacher) {

    private val binding: DialogAddTeacherBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnSave.setOnClickListener{
            val teacherName = binding.edTeacherName.text.toString()
            onAddClick(teacherName)
            dismiss()
        }
    }

}