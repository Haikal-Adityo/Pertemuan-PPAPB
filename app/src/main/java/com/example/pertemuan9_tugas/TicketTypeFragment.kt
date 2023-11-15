package com.example.pertemuan9_tugas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AdapterView
import androidx.navigation.fragment.findNavController
import com.example.pertemuan9_tugas.databinding.FragmentTicketTypeBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TicketTypeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TicketTypeFragment : Fragment() {

    private lateinit var binding : FragmentTicketTypeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTicketTypeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val spinnerTicketType = binding.spinnerTicketType
        val ticketTypeAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.ticket_type,
            android.R.layout.simple_spinner_item
        )
        ticketTypeAdapter.setDropDownViewResource(com.google.android.material.R.layout.support_simple_spinner_dropdown_item)
        spinnerTicketType.adapter = ticketTypeAdapter

        var selectedTicketType: String? = null

        spinnerTicketType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedTicketType = spinnerTicketType.selectedItem.toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        with(binding) {
            btnBuy.setOnClickListener {
                findNavController().apply {
                    previousBackStackEntry?.savedStateHandle?.
                            set("ticketType", selectedTicketType)
                }.navigateUp()
            }
        }
    }

}