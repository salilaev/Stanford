package qr.team_salilaev.stanford.presentation.screens.teacher.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import qr.team_salilaev.stanford.data.local.course.CourseEntity
import qr.team_salilaev.stanford.data.local.teacher.TeacherEntity
import qr.team_salilaev.stanford.databinding.ListItemTeacherBinding
import qr.team_salilaev.stanford.databinding.ScreenTeachersBinding

class TeacherAdapter: RecyclerView.Adapter<TeacherAdapter.TeacherViewHolder>() {

    private var teacherList : List<TeacherEntity> = emptyList()

    private var teacherItemClickListener: ((TeacherEntity)->Unit)? = null

    private var teacherItemDeleteListener: ((TeacherEntity)->Unit)? = null

    private var teacherItemEditListener: ((TeacherEntity)->Unit)? = null

    fun setTeacherItemClickListener(block: (TeacherEntity) -> Unit) {
        teacherItemClickListener = block
    }

    fun setTeacherItemDeleteListener(block: (TeacherEntity) -> Unit) {
        teacherItemDeleteListener = block
    }

    fun setTeacherItemEditListener(block: (TeacherEntity)-> Unit){
        teacherItemEditListener = block
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setTeacherList(list: List<TeacherEntity>) {
        teacherList = list
        notifyDataSetChanged()
    }

    inner class TeacherViewHolder(private val binding: ListItemTeacherBinding):RecyclerView.ViewHolder(binding.root){
        init {
            binding.root.setOnClickListener(){
                val item = teacherList[adapterPosition]
                teacherItemClickListener?.invoke(item)
            }

            binding.btnDelete.setOnClickListener {
                val item = teacherList[adapterPosition]
                teacherItemDeleteListener?.invoke(item)
            }

            binding.btnEdit.setOnClickListener {
                val item = teacherList[adapterPosition]
                teacherItemEditListener?.invoke(item)
            }
        }
        fun onBind(teacherEntity: TeacherEntity) {
            binding.apply {
                tvTeacherName.text = teacherEntity.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherViewHolder {
        return TeacherViewHolder(ListItemTeacherBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun getItemCount(): Int = teacherList.size

    override fun onBindViewHolder(holder: TeacherViewHolder, position: Int) {
       holder.onBind(teacherList[position])
    }

}