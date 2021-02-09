package kg.sunrise.sabs.app
import  kg.sunrise.sabs.ui.cash_box.CashBoxViewModel
import kg.sunrise.sabs.ui.cash_box.tab.main.CashPageViewModel
import kg.sunrise.sabs.ui.receipt.ReceiptViewModel
import kg.sunrise.sabs.ui.reports.ReportsViewModel
import kg.sunrise.sabs.ui.reports.tab.main.ReportsPageViewModel
import kg.sunrise.sabs.view_model.ApiActivityViewModel
import kg.sunrise.sabs.view_model.SecondApiActivityViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module{
    viewModel{ SecondApiActivityViewModel(get()) }
    single{ ApiActivityViewModel(get()) }
    single{ CashBoxViewModel() }
    single{ CashPageViewModel() }

    single{ ReceiptViewModel() }

    single{ ReportsViewModel() }
    single{ ReportsPageViewModel() }

}