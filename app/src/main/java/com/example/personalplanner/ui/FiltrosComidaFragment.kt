package com.example.personalplanner.ui

import android.os.Bundle
import android.view.*
import android.widget.SeekBar
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.listafacturas.viewmodel.ComidaViewModel
import com.example.personalplanner.R
import com.example.personalplanner.databinding.FragmentFiltrosComidaBinding
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class FiltrosComidaFragment : Fragment() {

    private var _binding: FragmentFiltrosComidaBinding? = null
    private val binding get() = _binding!!
    private val viewModel by activityViewModel<ComidaViewModel>()
    var tiempoMax: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFiltrosComidaBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initMenu()

        initViewModel()

        initSeekBar()

        onAplicarFiltros()

        onEliminarFiltros()
    }

    private fun onAplicarFiltros() {
        binding.btnAplicarFiltros.setOnClickListener {

            viewModel.isFiltered = true

            viewModel.tiempoMaxSelected = binding.sbTiempo.progress

            viewModel.isChecked =
                viewModel.bEntrante.value == true || viewModel.bBebidas.value == true || viewModel.bPrincipal.value == true || viewModel.bSegundo.value == true || viewModel.bPostre.value == true

            NavHostFragment.findNavController(this).navigateUp()
        }
    }

    private fun onEliminarFiltros() {
        binding.btnEliminarFiltros.setOnClickListener {

            // RatingBar
            binding.rbDificultad.rating = 5.0F

            // SeekBar
            binding.sbTiempo.progress = tiempoMax

            // Checkbox
            binding.viewmodel?.bEntrante?.value = false
            binding.viewmodel?.bBebidas?.value = false
            binding.viewmodel?.bPrincipal?.value = false
            binding.viewmodel?.bSegundo?.value = false
            binding.viewmodel?.bPostre?.value = false
        }
    }

    private fun initMenu() {
        val menu: MenuHost = requireActivity()

        menu.addMenuProvider(object: MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_close, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {

                return if (menuItem.itemId == R.id.action_close) {
                    NavHostFragment.findNavController(this@FiltrosComidaFragment).navigateUp()
                    true
                } else
                    false
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun initSeekBar() {
        tiempoMax = viewModel.tiempoMax
        binding.sbTiempo.max = tiempoMax
        binding.tvTiempoMax.text = tiempoMax.toString().plus(" min")

        val importeProgress = viewModel.tiempoMaxSelected
        binding.sbTiempo.progress = importeProgress
        binding.tvTiempo.text = getString(R.string.rango_tiempo, importeProgress.toString())

        binding.sbTiempo.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onStopTrackingTouch(seekBar: SeekBar) {
                    // Cuando se deja de mover el SeekBar
                }
                override fun onStartTrackingTouch(seekBar: SeekBar) {
                    // Cuando se comienza a mover el SeekBar
                }
                override fun onProgressChanged(
                    seekBar: SeekBar, progress: Int,
                    fromUser: Boolean
                ) {
                    binding.tvTiempo.text = getString(R.string.rango_tiempo, progress.toString())
                }
            }
        )
    }

    private fun initViewModel() {
        binding.viewmodel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}