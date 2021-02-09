package kg.sunrise.sabs.ui.restaurant.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kg.sunrise.sabs.R
import kg.sunrise.sabs.data.billing.RestaurantModel
import kotlinx.android.synthetic.main.list_profile.view.*

class RestourantAdapter : RecyclerView.Adapter<RestourantAdapter.ViewHolder>() {
    private var items: ArrayList<RestaurantModel> = ArrayList()
    private var onItemClickListener: OnClickListener? = null
    interface OnClickListener {
        fun onItemPositionClick(title: RestaurantModel, position: Int) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_profile, parent, false))
    }
    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(items[position])

    fun setClickListener(onItemClickListener: OnClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    fun swapData(data: ArrayList<RestaurantModel>) {
        this.items = data
        notifyDataSetChanged()
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(restaurant: RestaurantModel) = with(itemView) {
            tl_restaurant.text = restaurant.email
            rate.text = restaurant.password
            date_pl_list.text = restaurant.paid_before
            setOnClickListener {
                onItemClickListener?.onItemPositionClick(restaurant, adapterPosition)
            }
        }
    }
}