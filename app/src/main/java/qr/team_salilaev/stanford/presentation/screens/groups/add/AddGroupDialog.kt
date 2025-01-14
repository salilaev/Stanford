package qr.team_salilaev.stanford.presentation.screens.groups.add

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import qr.team_salilaev.stanford.R
import qr.team_salilaev.stanford.databinding.DialogAddGroupBinding

class AddGroupDialog(
    private val onAddClick: (String) -> Unit
) : DialogFragment(R.layout.dialog_add_group) {

    private val binding: DialogAddGroupBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnSave.setOnClickListener {
            val courseName = binding.idGroupName.text.toString()
            onAddClick(courseName)
            dismiss()
        }
    }

}