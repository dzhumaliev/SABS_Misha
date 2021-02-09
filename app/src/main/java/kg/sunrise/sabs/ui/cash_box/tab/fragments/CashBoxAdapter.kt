package kg.sunrise.sabs.ui.cash_box.tab.fragments
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kg.sunrise.sabs.R
import kg.sunrise.sabs.data.model.BillsData
import kg.sunrise.sabs.data.model.PaymentsData
import kotlinx.android.synthetic.main.list_cash_box.view.*
import kotlinx.android.synthetic.main.list_report.view.*
import kotlin.collections.ArrayList

class CashBoxAdapter : RecyclerView.Adapter<CashBoxAdapter.ViewHolder>() {
    private var items: ArrayList<BillsData> = ArrayList()
    private var onItemClickListener: OnClickListener? = null
    interface OnClickListener {
        fun onItemPositionClick(title: BillsData, position: Int) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_cash_box, parent, false))
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
            title_list_cb.text = bills.username
            bills_list_cb.text = bills.id.toString()
        }
    }
}