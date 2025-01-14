package qr.team_salilaev.stanford.presentation.screens.students.adapter
import android.annotation.SuppressLint
import android.net.http.HeaderBlock
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import qr.team_salilaev.stanford.data.local.group.GroupEntity
import qr.team_salilaev.stanford.data.local.student.StudentEntity
import qr.team_salilaev.stanford.data.model.GroupData
import qr.team_salilaev.stanford.databinding.ListItemStudentsBinding

class StudentAdapter: RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    private var studentList:List<StudentEntity> = emptyList()

    private var studentItemClickListener: ((StudentEntity) -> Unit)? = null

    private var studentItemDeleteListener:((StudentEntity)-> Unit)? = null

    private var studentItemEditListener: ((StudentEntity) -> Unit)? = null

    fun setStudentItemClickListener(block: (StudentEntity) -> Unit){
        studentItemClickListener = block
    }

    fun setStudentItemDeleteListener(block: (StudentEntity) -> Unit){
        studentItemDeleteListener = block
    }

    fun setStudentEditItemListener(block:(StudentEntity) -> Unit){
        studentItemEditListener = block
    }


    @SuppressLint("NotifyDataSetChanged")
    fun setStudentList(list:List<StudentEntity>){
        studentList = list
        notifyDataSetChanged()
    }

    inner class  StudentViewHolder(private val binding: ListItemStudentsBinding):RecyclerView.ViewHolder(binding.root){

        init {
            binding.root.setOnClickListener{
                val item = studentList[adapterPosition]
                studentItemClickListener?.invoke(item)
            }

            binding.btnDelete.setOnClickListener {
                val item = studentList[adapterPosition]
                studentItemDeleteListener?.invoke(item)
            }

            binding.btnEdit.setOnClickListener {
                val item = studentList[adapterPosition]
                studentItemEditListener?.invoke(item)
            }
        }



        fun onBind(studentEntity: StudentEntity){
            binding.apply {
                tvStudentName.text = studentEntity.studentName
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        return StudentViewHolder(ListItemStudentsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = studentList.size

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.onBind(studentList[position])
    }
}