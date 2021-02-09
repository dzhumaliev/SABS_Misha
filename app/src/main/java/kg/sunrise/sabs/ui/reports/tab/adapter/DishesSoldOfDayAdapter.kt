package kg.sunrise.sabs.ui.reports.tab.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kg.sunrise.sabs.R
import kg.sunrise.sabs.data.model.*
import kotlinx.android.synthetic.main.list_day.view.*
import kotlinx.android.synthetic.main.list_dishes_sold.view.*
import kotlinx.android.synthetic.main.list_report.view.*
import kotlinx.android.synthetic.main.list_report.view.product
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class DishesSoldOfDayAdapter : RecyclerView.Adapter<DishesSoldOfDayAdapter.ViewHolder>() {
    private var items: ArrayList<DishesSoldOfDayData> = ArrayList()

    private var onItemClickListener: OnClickListener? = null

    interface OnClickListener {
        fun onItemPositionClick(title: DishesSoldOfDayData, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_dishes_sold, parent, false)
        )
    }
    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(items[position])

    fun setClickListener(onItemClickListener: OnClickListener) {
        this.onItemClickListener = onItemClickListener
    }
    fun swapData(data: ArrayList<DishesSoldOfDayData>) {
        this.items = data
        notifyDataSetChanged()
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bind(dishesSoldDayData: DishesSoldOfDayData) = with(itemView) {
            product.text = dishesSoldDayData.dish
            drove.text = dishesSoldDayData.userName
            price.text = dishesSoldDayData.price
        }
    }
}