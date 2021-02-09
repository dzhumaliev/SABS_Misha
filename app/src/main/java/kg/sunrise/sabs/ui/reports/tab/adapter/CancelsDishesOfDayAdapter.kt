package kg.sunrise.sabs.ui.reports.tab.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kg.sunrise.sabs.R
import kg.sunrise.sabs.data.model.CancelsData
import kotlinx.android.synthetic.main.list_statistics_repeal.view.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class CancelsDishesOfDayAdapter : RecyclerView.Adapter<CancelsDishesOfDayAdapter.ViewHolder>() {
    private var items: ArrayList<CancelsData> = ArrayList()

    private var onItemClickListener: OnClickListener? = null
    interface OnClickListener {
        fun onItemPositionClick(title: CancelsData, position: Int) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_statistics_repeal, parent, false))
    }
    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(items[position])

    fun setClickListener(onItemClickListener: OnClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    fun swapData(data: ArrayList<CancelsData>) {
        this.items = data
        notifyDataSetChanged()
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(cancels: CancelsData) = with(itemView) {
            val hh = convertHH(cancels.time)
            text_time_statistics_list.text = hh
            product.text = cancels.dishName
            drove_list.text = cancels.billUserName
            repeal_list.text = cancels.cancelUserName
        }
    }
    @SuppressLint("SimpleDateFormat")
    fun convertHH(data: String): String {
        val form = SimpleDateFormat("dd/MM/yyy HH:mm")
        var date: Date? = null
        try {
            date = form.parse(data)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        val postFormater = SimpleDateFormat("HH:MM")
        return postFormater.format(date)
    }
}