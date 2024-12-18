package kabu.hayen.presentation.view.activity

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import org.koin.androidx.viewmodel.ext.android.viewModel
import kabu.hayen.R
import kabu.hayen.data.model.Bar
import kabu.hayen.data.model.Stock
import kabu.hayen.presentation.viewModel.DetailsViewModel
import kabu.hayen.presentation.viewModel.DetailsViewModelImpl

class StockDetailsActivity : AppCompatActivity() {
    private val viewModel: DetailsViewModel by viewModel<DetailsViewModelImpl>()

    override fun onResume() {
        super.onResume()
        init()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock_details)

        init()
    }

    private fun init() {
        showProgressBar()
        val symbol = intent.getStringExtra("symbol") ?: return
        supportActionBar?.title = "Stock details - $symbol"

        // ne dozvoljava drugim simbolima posto samo ima podatke o T
        if (symbol != "T") {
            Toast.makeText(this, "Symbol must be T", Toast.LENGTH_SHORT).show()
            finish()
        }

        fetchData(symbol)
    }

    private fun fetchData(symbol: String) {
        viewModel.fetchStock(symbol)
        viewModel.stock.observe(this) { response ->
            hideProgressBar()

            if (response != null && response.instrumentId != "INVALID") {
                //Log.d("RESPONSE DETAILS", response.instrumentId)
                initUi(response)
            }
            else {
                Toast.makeText(this, "Request to the server failed!",
                    Toast.LENGTH_SHORT).show()

                finish()
            }
        }
    }

    private fun initUi(stock: Stock) {
        val symbol: TextView = findViewById(R.id.symbolTv)
        val curVal: TextView = findViewById(R.id.currentValTv)
        val currency: TextView = findViewById(R.id.currencyTv)
        val open: TextView = findViewById(R.id.openTv)
        val close: TextView = findViewById(R.id.closeTv)
        val bid: TextView = findViewById(R.id.bidTv)
        val ask: TextView = findViewById(R.id.askTv)
        val alpha: TextView = findViewById(R.id.alphaTv)
        val beta: TextView = findViewById(R.id.betaTv)
        val sharp: TextView = findViewById(R.id.sharpTv)
        val mktCup: TextView = findViewById(R.id.mktCapTv)
        val eps: TextView = findViewById(R.id.epsTv)
        val ebit: TextView = findViewById(R.id.ebitTv)

        symbol.text = "Stock symbol: " + stock.symbol
        curVal.text = "Last value: " + stock.last
        currency.text = "Currency: " + stock.currency
        open.text = "OPEN: " + stock.open
        close.text = "CLOSE: " + stock.close
        bid.text = "BID: " + stock.bid
        ask.text = "ASK: " + stock.ask
        alpha.text = "ALPHA: " + stock.metrics.alpha
        beta.text = "BETA: " + stock.metrics.beta
        sharp.text = "SHARP: " + stock.metrics.sharp
        mktCup.text = "MK CUP: " + stock.metrics.marketCup
        eps.text = "EPS: " + stock.metrics.eps
        ebit.text = "EBIT: " + stock.metrics.ebit

        val change: Double = intent.getDoubleExtra("change", 0.toDouble())
        initListeners(stock, change)
        initChart(stock, change)
    }

    private fun initListeners(stock: Stock, change: Double) {
        val buyBtn: Button = findViewById(R.id.buyBtn)
        val sellBtn: Button = findViewById(R.id.sellBtn)

        buyBtn.setOnClickListener {
            val intent = Intent(this, BuyActivity::class.java)
            intent.putExtra("id", stock.instrumentId)
            intent.putExtra("name", stock.name)
            intent.putExtra("symbol", stock.symbol)
            intent.putExtra("last", stock.last)
            intent.putExtra("change", change)
            startActivity(intent)
        }

        viewModel.getBoughtStock(getUsername(), stock.symbol)
        viewModel.boughtStock.observe(this) { boughtStock ->
            if (boughtStock != null) {
                sellBtn.visibility = View.VISIBLE

                sellBtn.setOnClickListener {
                    val intent = Intent(this, SellActivity::class.java)
                    intent.putExtra("id", stock.instrumentId)
                    intent.putExtra("name", stock.name)
                    intent.putExtra("symbol", stock.symbol)
                    intent.putExtra("last", stock.last)
                    intent.putExtra("change", change)
                    startActivity(intent)
                }
            } else sellBtn.visibility = View.GONE
        }
    }

    private fun initChart(stock: Stock, change: Double) {
        val chart: LineChart = findViewById(R.id.stockChart)
        val entries = ArrayList<Entry>()

        for (bar: Bar in stock.chart.bars)
            entries.add(Entry(entries.size.toFloat(), (bar.price).toFloat()))

        val set = LineDataSet(entries, "Stock")

        if (change < 0f) {
            set.color = Color.RED
            set.setCircleColor(Color.RED)
        }
        else {
            set.color = Color.GREEN
            set.setCircleColor(Color.GREEN)
        }

        val dataSet = ArrayList<ILineDataSet>()
        dataSet.add(set)
        val data = LineData(dataSet)
        chart.data = data
        chart.invalidate()
    }

    private fun showProgressBar() {
        val progress: ProgressBar = findViewById(R.id.detailsProgBar)
        progress.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        val progress: ProgressBar = findViewById(R.id.detailsProgBar)
        progress.visibility = View.GONE
    }

    private fun getUsername(): String {
        val sp: SharedPreferences = getSharedPreferences(
            "userInfo",
            AppCompatActivity.MODE_PRIVATE
        )
        return sp.getString("username", "")!!
    }
}