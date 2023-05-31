package com.example.personalplanner.ui

import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.NavHostFragment
import com.example.personalplanner.R
import com.example.personalplanner.databinding.FragmentGastosBinding
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry

class GastosFragment : Fragment() {

    lateinit var barChart: BarChart

    lateinit var barData: BarData

    lateinit var barDataSet: BarDataSet

    lateinit var barEntriesList: ArrayList<BarEntry>

    private var _binding: FragmentGastosBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentGastosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initMenu()

        barChart = binding.idBarChart

        getBarChartData()

        // on below line we are initializing our bar data set
        barDataSet = BarDataSet(barEntriesList, "Bar Chart Data")

        // on below line we are initializing our bar data
        barData = BarData(barDataSet)

        // on below line we are setting data to our bar chart
        barChart.data = barData

        // on below line we are setting colors for our bar chart text
        barDataSet.valueTextColor = Color.BLACK

        // on below line we are setting color for our bar data set
        barDataSet.setColor(resources.getColor(R.color.purple_200))

        // on below line we are setting text size
        barDataSet.valueTextSize = 16f

        // on below line we are enabling description as false
        barChart.description.isEnabled = false

    }

    private fun initMenu() {
        val menu: MenuHost = requireActivity()

        menu.addMenuProvider(object: MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_close, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return if (menuItem.itemId == R.id.action_close) {
                    NavHostFragment.findNavController(this@GastosFragment).navigate(R.id.action_gastosFragment_to_modificarImporteGastosFragment)
                    true
                } else {
                    false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun getBarChartData() {
        barEntriesList = ArrayList()

        // on below line we are adding data
        // to our bar entries list
        barEntriesList.add(BarEntry(1f, 1f))
        barEntriesList.add(BarEntry(2f, 2f))
        barEntriesList.add(BarEntry(3f, 3f))
        barEntriesList.add(BarEntry(4f, 4f))
        barEntriesList.add(BarEntry(5f, 5f))
        barEntriesList.add(BarEntry(6f, 6f))
        barEntriesList.add(BarEntry(7f, 7f))
        barEntriesList.add(BarEntry(8f, 8f))
        barEntriesList.add(BarEntry(9f, 9f))
        barEntriesList.add(BarEntry(10f, 10f))
        barEntriesList.add(BarEntry( 11f, 11f))
        barEntriesList.add(BarEntry(12f, 12f))

    }


}