package kabu.hayen.presentation.view.adapter

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import kabu.hayen.R
import kabu.hayen.data.model.Bar
import kabu.hayen.data.model.Quote
import kabu.hayen.presentation.view.activity.StockDetailsActivity

class StocksRecyclerAdapter(private var list: ArrayList<Quote>,
    private var activity: Activity) :
    RecyclerView.Adapter<StocksRecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var symbol: TextView = view.findViewById(R.id.symbolRight)
        var name: TextView = view.findViewById(R.id.stockName)
        var lastPrice: TextView = view.findViewById(R.id.lastPrice)
        var chart: LineChart = view.findViewById(R.id.stockChart)
        var card: ConstraintLayout = view.findViewById(R.id.cardStocks)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.card_view_stocks,
            parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.symbol.text = list[position].symbol
        holder.name.text = "Name: " + list[position].name
        holder.lastPrice.text =
            "Last price: " + list[position].last.toString() + list[position].currency

        // chart
        val entries = ArrayList<Entry>()

        for (bar: Bar in list[position].chart.bars)
            entries.add(Entry(entries.size.toFloat(), (bar.price).toFloat()))

        val set = LineDataSet(entries, "Stock")

        if (list[position].changeFromPreviousClose < 0f) {
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
        holder.chart.data = data
        holder.chart.invalidate()

        // stock details
        holder.card.setOnClickListener {
            val intent = Intent(activity, StockDetailsActivity::class.java)
            intent.putExtra("symbol", list[position].symbol)
            intent.putExtra("change", list[position].changeFromPreviousClose)
            activity.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}