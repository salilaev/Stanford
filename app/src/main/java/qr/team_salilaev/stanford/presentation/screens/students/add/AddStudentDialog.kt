package qr.team_salilaev.stanford.presentation.screens.students.add

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import qr.team_salilaev.stanford.R
import qr.team_salilaev.stanford.databinding.DialogAddStudentBinding

class AddStudentDialog(
    private val onAddClick: (String, String, String) -> Unit
) : DialogFragment(R.layout.dialog_add_student) {

    private val binding: DialogAddStudentBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnSave.setOnClickListener {
            val studentName = binding.edStudentName.text.toString()
            val studentAge = binding.edStudentName.text.toString()
            val studentLocation = binding.edStudentLocation.text.toString()
            onAddClick(studentName, studentAge, studentLocation)
            dismiss()
        }
    }
}