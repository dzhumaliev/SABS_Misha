@file:Suppress("DEPRECATION")
package kg.sunrise.sabs.ui.reports.tab.main
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import kg.sunrise.sabs.R
import kg.sunrise.sabs.ui.reports.tab.fragments.TabDishesSoldFragment
import kg.sunrise.sabs.ui.reports.tab.fragments.TabLeftoversFragment
import kg.sunrise.sabs.ui.reports.tab.fragments.TabAbolitionFragment
import kg.sunrise.sabs.ui.reports.tab.fragments.TabTopSellingFragment

private val TAB_TITLES = arrayOf(
    R.string.tab_abolition,
    R.string.tab_top_selling,
    R.string.tab_dishes_sold,
    R.string.tab_stock
)
class ReportsSPAdapterTab(private val context: FragmentActivity?, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {
    private var push: String = ""
    private var tabAF = TabAbolitionFragment()
    private var tabTSF = TabTopSellingFragment()
    private var tabDSF = TabDishesSoldFragment()
    private var tabLF = TabLeftoversFragment()

    fun dateFunc(startDate:String, endDate:String, spinner: String): String {
        tabAF.dateFunc(startDate, endDate, spinner)
        tabTSF.dateFunc(startDate, endDate)
        tabDSF.dateFunc(startDate, endDate, spinner)
        tabLF.dateFunc(startDate, endDate, spinner)
        return push
    }

    override fun getItem(position: Int): Fragment {
        val bundle1 = Bundle()
        when (position) {
            0 -> {
                tabAF.arguments = bundle1
                return tabAF
            }
            1 -> {
                tabTSF.arguments = bundle1
                return tabTSF
            }
            2 -> {
                tabDSF.arguments = bundle1
                return tabDSF
            }
            3 -> {
                tabLF.arguments = bundle1
                return tabLF
            }
            else ->
                null!!
        }
    }
    override fun getPageTitle(position: Int): CharSequence? {
        return context?.resources?.getString(TAB_TITLES[position])
    }
    override fun getCount(): Int {
        return TAB_TITLES.size
    }
}