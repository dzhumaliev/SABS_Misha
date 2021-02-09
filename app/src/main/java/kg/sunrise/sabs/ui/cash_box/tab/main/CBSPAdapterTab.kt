package kg.sunrise.sabs.ui.cash_box.tab.main
import android.os.Bundle
import androidx.fragment.app.*
import kg.sunrise.sabs.R
import kg.sunrise.sabs.ui.cash_box.tab.fragments.TabRevenueByTheHourFragment
import kg.sunrise.sabs.ui.cash_box.tab.fragments.TabStatisticsFragment

private val TAB_TITLES = arrayOf(
    R.string.tab_statistics,
    R.string.tab_revenue_by_the_hour
)

class CBSPAdapterTab(private val context: FragmentActivity, fm: FragmentManager
) : FragmentPagerAdapter(fm) {
    private var push: String = ""
    private var tabStatistic = TabStatisticsFragment()
    private var tabRBTH = TabRevenueByTheHourFragment()

    override fun getItem(position: Int): Fragment {
        val bundle1 = Bundle()
        when (position) {
            0 -> {
                tabStatistic.arguments = bundle1
                return tabStatistic
            }
            1 -> {
                tabRBTH.arguments = bundle1
                return tabRBTH
            }
            else-> null!!
        }
    }
    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources?.getString(TAB_TITLES[position])
    }
    override fun getCount(): Int {
        return 2
    }
    fun dateFunc(startDate:String, endDate:String): String {
        tabRBTH.dateFunc(startDate, endDate)
        tabStatistic.dateFunc(startDate, endDate)
        return push
    }
}