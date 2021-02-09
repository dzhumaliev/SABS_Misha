package kg.sunrise.sabs.ui.reports.tab.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kg.sunrise.sabs.R
import kg.sunrise.sabs.data.model.DishesSoldDateData
import kotlinx.android.synthetic.main.list_day.view.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class DishesSoldOfDateAdapter : RecyclerView.Adapter<DishesSoldOfDateAdapter.ViewHolder>() {
    private var items: ArrayList<DishesSoldDateData> = ArrayList()

    private var onItemClickListener: OnClickListener? = null
    interface OnClickListener {
        fun onItemPositionClick(title: DishesSoldDateData, position: Int) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_day, parent, false))
    }
    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(items[position])

    fun setClickListener(onItemClickListener: OnClickListener) {
        this.onItemClickListener = onItemClickListener
    }
    fun swapData(data: ArrayList<DishesSoldDateData>) {
        this.items = data
        notifyDataSetChanged()
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bind(dishesSoldDateData: DishesSoldDateData) = with(itemView) {
            val hh = convertHH(dishesSoldDateData.soldDishesDate)
            average_check.text = "Всего продано на сумму:"
            day.text = dishesSoldDateData.soldDishesDateAndMonth
            day_name.text = " / $hh"
            check.text = dishesSoldDateData.amountOfSoldDishes+" KGS"
            setOnClickListener {
                onItemClickListener?.onItemPositionClick(dishesSoldDateData, adapterPosition)
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