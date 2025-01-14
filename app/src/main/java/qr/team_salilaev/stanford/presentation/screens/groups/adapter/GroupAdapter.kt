package qr.team_salilaev.stanford.presentation.screens.groups.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import qr.team_salilaev.stanford.data.local.course.CourseEntity
import qr.team_salilaev.stanford.data.local.group.GroupEntity
import qr.team_salilaev.stanford.data.local.group.GroupWithStudentRelation
import qr.team_salilaev.stanford.data.model.GroupData
import qr.team_salilaev.stanford.databinding.ListItemGroupBinding

class GroupAdapter: RecyclerView.Adapter<GroupAdapter.GroupsViewHolder>() {

    private var groupList:List<GroupWithStudentRelation> = emptyList()

    private var groupItemClickListener: ((GroupWithStudentRelation) -> Unit)? = null

    private var groupItemDeleteListener: ((GroupWithStudentRelation)-> Unit)? = null

    private var groupItemEditListener: ((GroupWithStudentRelation)-> Unit)? = null

    fun setGroupItemListener(block: (GroupWithStudentRelation) -> Unit){
        groupItemClickListener = block
    }

    fun setGroupItemDeleteListener(block: (GroupWithStudentRelation) -> Unit){
        groupItemDeleteListener = block
    }

    fun setGroupEditItemListener(block:(GroupWithStudentRelation) -> Unit){
        groupItemEditListener = block
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setGroupList(list: List<GroupWithStudentRelation>) {
        groupList = list
        notifyDataSetChanged()
    }


    inner class  GroupsViewHolder(private val binding: ListItemGroupBinding):RecyclerView.ViewHolder(binding.root){

        init {
            binding.root.setOnClickListener{
                val item = groupList[adapterPosition]
                groupItemClickListener?.invoke(item)
            }

             binding.btnDelete.setOnClickListener {
                val item = groupList[adapterPosition]
                groupItemDeleteListener?.invoke(item)
            }

            binding.btnEditGroup.setOnClickListener {
                val item = groupList[adapterPosition]
                groupItemEditListener?.invoke(item)
            }

        }

        fun onBind(groupData: GroupWithStudentRelation){
            binding.apply {
                idGroupName.text = groupData.groupEntity.groupName
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupsViewHolder {
        return GroupsViewHolder(ListItemGroupBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = groupList.size

    override fun onBindViewHolder(holder: GroupsViewHolder, position: Int) {
        holder.onBind(groupList[position])
    }
}