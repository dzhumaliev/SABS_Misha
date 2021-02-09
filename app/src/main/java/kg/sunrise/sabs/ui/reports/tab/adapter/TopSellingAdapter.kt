package kg.sunrise.sabs.ui.reports.tab.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kg.sunrise.sabs.R
import kg.sunrise.sabs.data.model.CancelsData
import kg.sunrise.sabs.data.model.TopSellingData
import kotlinx.android.synthetic.main.list_ts_pch.view.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class TopSellingAdapter : RecyclerView.Adapter<TopSellingAdapter.ViewHolder>() {
    private var items: ArrayList<TopSellingData> = ArrayList()

    private var onItemClickListener: OnClickListener? = null
    interface OnClickListener {
        fun onItemPositionClick(title: TopSellingData, position: Int) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_ts_pch, parent, false))
    }
    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(items[position])

    fun setClickListener(onItemClickListener: OnClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    fun swapData(data: ArrayList<TopSellingData>) {
        this.items = data
        notifyDataSetChanged()
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(ts: TopSellingData) = with(itemView) {
            product.text = ts.dishName
            repeal_list.text = ts.percentCount.toString()+"%"
        }
    }
//    fun convertHH(data: String): String {
//        val form = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
//        var date: Date? = null
//        try {
//            date = form.parse(data)
//        } catch (e: ParseException) {
//            e.printStackTrace()
//        }
//        val postFormater = SimpleDateFormat("HH:MM")
//        return postFormater.format(date)
//    }
}