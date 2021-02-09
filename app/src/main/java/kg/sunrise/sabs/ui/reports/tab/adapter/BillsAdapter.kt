package kg.sunrise.sabs.ui.reports.tab.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kg.sunrise.sabs.R
import kg.sunrise.sabs.data.model.BillsData
import kotlinx.android.synthetic.main.list_report.view.*

class BillsAdapter : RecyclerView.Adapter<BillsAdapter.ViewHolder>() {
    private var items: ArrayList<BillsData> = ArrayList()
    private var onItemClickListener: OnClickListener? = null
    interface OnClickListener {
        fun onItemPositionClick(title: BillsData, position: Int) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_report, parent, false))
    }
    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(items[position])

    fun setClickListener(onItemClickListener: OnClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    fun swapData(data: ArrayList<BillsData>) {
        this.items = data
        notifyDataSetChanged()
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(bills: BillsData) = with(itemView) {
            product.text = bills.iteM_NAME
            quantity_list.text = bills.username.toString()
            price_list.text = bills.iteM_PRICE.toString()
            setOnClickListener {
                onItemClickListener?.onItemPositionClick(bills, adapterPosition)
            }
        }
    }
}