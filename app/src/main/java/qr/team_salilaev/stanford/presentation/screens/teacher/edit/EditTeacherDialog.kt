package qr.team_salilaev.stanford.presentation.screens.teacher.edit

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import qr.team_salilaev.stanford.R
import qr.team_salilaev.stanford.data.local.teacher.TeacherEntity
import qr.team_salilaev.stanford.databinding.DialogEditTeacherBinding

class EditTeacherDialog (private val teacherEntity: TeacherEntity, private val onEditClick: (String) -> Unit) : DialogFragment(R.layout.dialog_edit_teacher) {

    private val binding: DialogEditTeacherBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.edTeacherName.setText(teacherEntity.name)
        binding.btnEdit.setOnClickListener {
            val teacherName = binding.edTeacherName.text.toString()
            onEditClick.invoke(teacherName)
            dismiss()
        }
    }
}