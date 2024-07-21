package com.example.trysealdmenu

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

object ReportType {
    const val VISIT_REPORT: Int = 0
    const val TRANSACTION_REPORT: Int = 1
    const val CUSTOMER_REPORT: Int = 2
}

object CategoryType {
    const val VISIT_REPORT: Int = 0
    const val TRANSACTION_REPORT: Int = 1
    const val CUSTOMER_REPORT: Int = 2
}

open class Report(
    open val id: Int,
    @StringRes open val R_string: Int,
    @DrawableRes open val R_drawable: Int,
    open var isFavorite: Boolean = false,
    open val onClick: () -> Unit = {}
)

open class Category(
    open val id: Int,
    @StringRes open val R_string: Int,
    @DrawableRes open val R_drawable: Int,
    open var isExpand: Boolean = false,
    open var reports: ArrayList<Report> = ArrayList()
)

sealed class ReportProvider {

    data class VisitReport(
        override var isFavorite: Boolean = false,
        override val onClick: () -> Unit = {}
    ) :
        Report(
            ReportType.VISIT_REPORT,
            R.string.visit_report,
            R.drawable.ic_visit,
            isFavorite,
            onClick
        )

    data class TransactionReport(
        override var isFavorite: Boolean = false,
        override val onClick: () -> Unit = {}
    ) :
        Report(
            ReportType.TRANSACTION_REPORT,
            R.string.visit_report,
            R.drawable.ic_transaction,
            false,
            onClick
        )

    data class CustomerReport(
        override var isFavorite: Boolean = false,
        override val onClick: () -> Unit = {}
    ) :
        Report(
            ReportType.CUSTOMER_REPORT,
            R.string.customers_report,
            R.drawable.ic_customer,
            false,
            onClick
        )

    fun getReport(id: Int): Report {
        when (id) {
            ReportType.VISIT_REPORT -> return VisitReport {}
            ReportType.TRANSACTION_REPORT -> return TransactionReport {}
            ReportType.CUSTOMER_REPORT -> return CustomerReport {}
        }
        throw RuntimeException()
    }
}


sealed class CategoryProvider {

    data object TransactionsCategory :
        Category(
            ReportType.TRANSACTION_REPORT,
            R.string.visit_report,
            R.drawable.ic_transaction
        )

    data object CustomersCategory :
        Category(
            ReportType.CUSTOMER_REPORT,
            R.string.customers_report,
            R.drawable.ic_customer
        )
}

