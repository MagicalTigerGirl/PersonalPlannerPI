package com.example.personalplanner.ui

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.listafacturas.viewmodel.ComidaViewModel
import com.example.listafacturas.viewmodel.StateDataList
import com.example.personalplanner.R
import com.example.personalplanner.data.adapter.ComidaAdapter
import com.example.personalplanner.data.model.Comida
import com.example.personalplanner.databinding.FragmentListaComidaBinding
import org.koin.androidx.viewmodel.ext.android.activityViewModel


class ListaComidaFragment : Fragment(), ComidaAdapter.OnManageFacturaListener {

    private var _binding: FragmentListaComidaBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ComidaAdapter
    private val viewModel by activityViewModel<ComidaViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentListaComidaBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initMenu()

        initRecyclerView()

        initViewModel()
    }

    private fun initMenu() {
        val menu: MenuHost = requireActivity()

        menu.addMenuProvider(object: MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_lista_factura, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return if (menuItem.itemId == R.id.action_filter) {
                    NavHostFragment.findNavController(this@ListaComidaFragment).navigate(R.id.action_SecondFragment_to_filtrosComidaFragment)
                    true
                } else {
                    false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun initRecyclerView() {
        adapter = ComidaAdapter(this)
        binding.rvFacturas.adapter = adapter
    }

    private fun initViewModel() {

        viewModel.liveDataList.observe(viewLifecycleOwner) {
            when (it.state) {
                StateDataList.DataState.SUCCESS -> {
                    binding.tvNoData.visibility = View.INVISIBLE
                    binding.prbLoading.visibility = View.INVISIBLE
                    adapter.update(it.data)
                }
                StateDataList.DataState.NODATA -> {
                    binding.prbLoading.visibility = View.INVISIBLE
                    binding.tvNoData.visibility = View.VISIBLE
                }
                else -> {
                    StateDataList.DataState.ERROR
                }
            }
        }

        if (viewModel.isFiltered)
            viewModel.getDataList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onShowFactura(comida: Comida) {
        val bundle = Bundle()
        bundle.putParcelable("food", comida)

        NavHostFragment.findNavController(this@ListaComidaFragment).navigate(R.id.action_SecondFragment_to_descriptionFoodFragment, bundle)
    }
}