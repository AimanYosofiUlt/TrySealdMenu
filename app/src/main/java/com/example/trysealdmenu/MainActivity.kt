package com.example.trysealdmenu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import com.example.trysealdmenu.ui.theme.TrySealdMenuTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TrySealdMenuTheme {
                Greeting()
            }
        }
    }
}

@Composable
fun Greeting() {
    val showVisitReport = false
    ReportBuilder.build {
        if (showVisitReport)
            visitReport(isFavorite = true) {

            }

        transactionReport {

        }

        customerReport {

        }
    }
}

class ReportBuilder {
    private var reports = ArrayList<Report>()

    private fun addReport(report: Report) {
        reports.add(report)
    }

    companion object {
        inline fun build(builder: ReportBuilder.() -> Unit) {
            ReportBuilder().apply(builder)
        }
    }

    fun visitReport(isFavorite: Boolean = false, onClick: () -> Unit = {}) =
        ReportProvider.VisitReport(isFavorite, onClick).apply { addReport(this) }

    fun transactionReport(isFavorite: Boolean = false, onClick: () -> Unit = {}) =
        ReportProvider.TransactionReport(isFavorite, onClick).apply { addReport(this) }

    fun customerReport(isFavorite: Boolean = false, onClick: () -> Unit = {}) =
        ReportProvider.CustomerReport(isFavorite, onClick).apply { addReport(this) }
}



