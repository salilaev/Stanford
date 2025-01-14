package qr.team_salilaev.stanford.presentation.screens.groups.edit

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import qr.team_salilaev.stanford.R
import qr.team_salilaev.stanford.data.local.group.GroupEntity
import qr.team_salilaev.stanford.databinding.DialogEditGroupBinding

class EditGroupDialog(private val groupDialog: GroupEntity, private val onEditClick: (String) -> Unit): DialogFragment(R.layout.dialog_edit_group) {

    private val binding : DialogEditGroupBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.edGroupName.setText(groupDialog.groupName)
        binding.btnEdit.setOnClickListener{
            val groupName = binding.edGroupName.text.toString()
            onEditClick.invoke(groupName)
            dismiss()
        }
    }

}