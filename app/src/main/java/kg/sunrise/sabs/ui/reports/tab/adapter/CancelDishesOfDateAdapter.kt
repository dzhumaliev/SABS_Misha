package kg.sunrise.sabs.ui.reports.tab.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kg.sunrise.sabs.R
import kg.sunrise.sabs.data.model.CancelsData
import kg.sunrise.sabs.data.model.CancelsDishesOfDayData
import kotlinx.android.synthetic.main.list_day.view.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class CancelDishesOfDateAdapter : RecyclerView.Adapter<CancelDishesOfDateAdapter.ViewHolder>() {
    private var items: ArrayList<CancelsDishesOfDayData> = ArrayList()

    private var onItemClickListener: OnClickListener? = null
    interface OnClickListener {
        fun onItemPositionClick(title: CancelsDishesOfDayData, position: Int) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_day, parent, false))
    }
    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(items[position])

    fun setClickListener(onItemClickListener: OnClickListener) {
        this.onItemClickListener = onItemClickListener
    }
    fun swapData(data: ArrayList<CancelsDishesOfDayData>) {
        this.items = data
        notifyDataSetChanged()
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bind(cancelsDishesOdDay: CancelsDishesOfDayData) = with(itemView) {
            val hh = convertHH(cancelsDishesOdDay.cancelDishesDate)
            average_check.text = "Всего отменено:"
            day.text = cancelsDishesOdDay.cancelDishesDateAndMonth
            day_name.text = " / $hh"
            check.text = cancelsDishesOdDay.amountOfCancelDishes+" KGS"
            setOnClickListener {
                onItemClickListener?.onItemPositionClick(cancelsDishesOdDay, adapterPosition)
            }
        }
    }
    @SuppressLint("SimpleDateFormat")
    fun convertHH(data: String): String {
        val form = SimpleDateFormat("dd/MM/yyy")
        var date: Date? = null
        try {
            date = form.parse(data)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        val postFormater = SimpleDateFormat("EEE")
        return postFormater.format(date)
    }
}