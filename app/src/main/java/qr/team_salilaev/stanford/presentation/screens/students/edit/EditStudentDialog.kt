package qr.team_salilaev.stanford.presentation.screens.students.edit

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import qr.team_salilaev.stanford.R
import qr.team_salilaev.stanford.data.local.student.StudentEntity
import qr.team_salilaev.stanford.databinding.DialogEditStudentBinding

class EditStudentDialog (private val studentDialog: StudentEntity, private val onEditClick: (String,String,String) -> Unit): DialogFragment(R.layout.dialog_edit_student) {

    private val binding : DialogEditStudentBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.editStudentName.setText(studentDialog.studentName)
        binding.editStudentAge.setText(studentDialog.studentAge)
        binding.editStudentLocation.setText(studentDialog.studentLocation)
        binding.btnEdit.setOnClickListener{
            val studentName = binding.editStudentName.text.toString()
            val studentAge = binding.editStudentAge.text.toString()
            val studentLocation = binding.editStudentLocation.text.toString()
            onEditClick.invoke(studentName,studentAge,studentLocation)
            dismiss()
        }
    }

}