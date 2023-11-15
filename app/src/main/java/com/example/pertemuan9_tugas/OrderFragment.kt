package com.example.pertemuan9_tugas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.pertemuan9_tugas.databinding.FragmentOrderBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OrderFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OrderFragment : Fragment() {

    private lateinit var binding : FragmentOrderBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrderBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            // * TO TICKET TYPE
            val action = OrderFragmentDirections.actionOrderFragmentToTicketTypeFragment()
            editTicketType.setOnClickListener{
                findNavController().navigate(action)
            }

            // * SET TICKET TYPE FROM "TICKET TYPE FRAGMENT"
            findNavController().currentBackStackEntry?.savedStateHandle.let {
                handle->
                handle?.getLiveData<String>("ticketType")?.observe(viewLifecycleOwner) { res->
                    editTicketType.setText(res)
                }
            }

            btnBuy.setOnClickListener {
                if(editTicketType.text.toString().isEmpty()) {
                    editTicketType.error = "Mohon pilih jenis tiket"
                } else {
                    findNavController().navigateUp()
                }
            }

        }
    }

}