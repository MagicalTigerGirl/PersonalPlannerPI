package com.example.personalplanner.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.listafacturas.viewmodel.ComidaViewModel
import com.example.personalplanner.data.model.Comida
import com.example.personalplanner.databinding.FragmentDescripcionComidaBinding
import org.koin.androidx.viewmodel.ext.android.activityViewModel


class DescripcionComidaFragment : Fragment() {

    private var _binding: FragmentDescripcionComidaBinding? = null
    private val binding get() = _binding!!
    private val viewModel by activityViewModel<ComidaViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDescripcionComidaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar = binding.flexibleExampleToolbar
        toolbar.setNavigationOnClickListener {
            NavHostFragment.findNavController(this).navigateUp()
        }

        val comida: Comida? = requireArguments().getParcelable("food")

        if (comida != null) {
            binding.comida = comida
            binding.flexibleExampleCollapsing.title = comida.nombre
            val ingredientNames = StringBuilder()
            for (ingredient in comida.ingredientes!!) {
                ingredientNames.append(ingredient.nombre).append(": ").append(ingredient.cantidad).append("\n")
            }
            binding.tvIngredientes.text = ingredientNames.toString()
            binding.tvTiempo.text = "Aproximadamente " + comida.tiempo.toString() + " min"
        }
    }
}