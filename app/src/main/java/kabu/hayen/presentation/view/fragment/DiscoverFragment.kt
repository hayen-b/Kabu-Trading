package kabu.hayen.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.viewModel
import kabu.hayen.R
import kabu.hayen.data.model.News
import kabu.hayen.data.model.Quote
import kabu.hayen.databinding.FragmentDiscoverBinding
import kabu.hayen.presentation.view.adapter.NewsRecyclerAdapter
import kabu.hayen.presentation.view.adapter.StocksRecyclerAdapter
import kabu.hayen.presentation.viewModel.DiscoverViewModel
import kabu.hayen.presentation.viewModel.DiscoverViewModelImpl

class DiscoverFragment : Fragment() {
    private val viewModel: DiscoverViewModel by viewModel<DiscoverViewModelImpl>()
    private var _binding: FragmentDiscoverBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDiscoverBinding.inflate(inflater, container, false)
        val root: View = binding.root
        init()
        return root
    }

    private fun init() {
        showProgressBars()
        fetchData()
    }

    private fun fetchData() {
        fetchNews()
        fetchStocks()
    }

    private fun fetchNews() {
        viewModel.fetchNews()
        viewModel.newsResponse.observe(viewLifecycleOwner) { response ->
            hideNewsProgressBar()

            if (response != null && response.isNotEmpty()) {
                //Log.d("RESPONSE NEWS", response.toString())
                val list = response as ArrayList<News>
                val recView: RecyclerView = binding.root.findViewById(R.id.newsRecView)
                val recAdapter = NewsRecyclerAdapter(list, requireActivity())
                val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(
                    requireContext().applicationContext, LinearLayoutManager.HORIZONTAL, false
                )
                recView.layoutManager = layoutManager
                recView.itemAnimator = DefaultItemAnimator()
                recView.adapter = recAdapter
            }
            else Toast.makeText(context, "Request to the server failed!",
                Toast.LENGTH_SHORT).show()
        }
    }

    private fun fetchStocks() {
        viewModel.fetchStocks()
        viewModel.indexResponse.observe(viewLifecycleOwner) { response ->
            hideStocksProgressBar()

            if (response != null && response.isNotEmpty()) {
                //Log.d("RESPONSE STOCKS", response.toString())
                val list = response as ArrayList<Quote>
                val recView: RecyclerView = binding.root.findViewById(R.id.stocksRecView)
                val recAdapter = StocksRecyclerAdapter(list, requireActivity())
                val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(
                    requireContext().applicationContext
                )
                recView.layoutManager = layoutManager
                recView.itemAnimator = DefaultItemAnimator()
                recView.adapter = recAdapter
            }
            else Toast.makeText(context, "Request to the server failed!",
                Toast.LENGTH_SHORT).show()
        }
    }

    private fun showProgressBars() {
        val newsProgress: ProgressBar = binding.root.findViewById(R.id.newsProgress)
        val stocksProgress: ProgressBar = binding.root.findViewById(R.id.stocksProgress)
        newsProgress.visibility = View.VISIBLE
        stocksProgress.visibility = View.VISIBLE
    }

    private fun hideNewsProgressBar() {
        val newsProgress: ProgressBar = binding.root.findViewById(R.id.newsProgress)
        newsProgress.visibility = View.GONE
    }

    private fun hideStocksProgressBar() {
        val stocksProgress: ProgressBar = binding.root.findViewById(R.id.stocksProgress)
        stocksProgress.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}