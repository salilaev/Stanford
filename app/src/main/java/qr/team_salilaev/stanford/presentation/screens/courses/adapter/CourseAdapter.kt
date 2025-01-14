package qr.team_salilaev.stanford.presentation.screens.courses.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import qr.team_salilaev.stanford.data.local.course.CourseWithGroupRelation
import qr.team_salilaev.stanford.databinding.ListItemBoardBinding

class CourseAdapter : RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {

    private var courseList: List<CourseWithGroupRelation> = emptyList()

    private var courseItemClickListener: ((CourseWithGroupRelation) -> Unit)? = null

    private var courseItemDeleteListener: ((CourseWithGroupRelation)->Unit)? = null

    private var courseItemEditListener: ((CourseWithGroupRelation)->Unit)? = null


    fun setCourseItemClickListener(block: (CourseWithGroupRelation) -> Unit) {
        courseItemClickListener = block
    }

    fun setCourseItemDeleteListener(block: (CourseWithGroupRelation) -> Unit) {
        courseItemDeleteListener = block
    }

    fun setCourseItemEditListener(block: (CourseWithGroupRelation)-> Unit){
        courseItemEditListener = block
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setCourseList(list: List<CourseWithGroupRelation>) {
        courseList = list
        notifyDataSetChanged()
    }


    inner class CourseViewHolder(private val binding: ListItemBoardBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val item = courseList[adapterPosition]
                courseItemClickListener?.invoke(item)
            }

            binding.btnDelete.setOnClickListener {
                val item = courseList[adapterPosition]
                courseItemDeleteListener?.invoke(item)
            }

            binding.btnEdit.setOnClickListener {
                val item = courseList[adapterPosition]
                courseItemEditListener?.invoke(item)
            }
        }

        fun onBind(relation: CourseWithGroupRelation) {
            binding.apply {
                tvCourseName.text = relation.courseEntity.courseName
                tvGroupCount.text = relation.list.size.toString()
                tvStudentCount.text = relation.list.sumOf { it.list.size }.toString()
            }
        }
    }

    //list(2,3,4)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        return CourseViewHolder(ListItemBoardBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = courseList.size

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.onBind(courseList[position])
    }
}