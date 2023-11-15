package com.example.pertemuan8_tugas

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pertemuan8_tugas.databinding.FragmentLoginBinding
import com.example.pertemuan8_tugas.databinding.FragmentRegisterBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentLoginBinding? = null
    private val binding get()= _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root

        with(binding) {

            btnLogin.setOnClickListener{
                if(editTxtName.text!!.isEmpty()) {
                    editTxtName.error = "Mohon Isi Username"
                } else if(editTxtPassword.text!!.isEmpty()) {
                    editTxtPassword.error = "Mohon Isi Password"
                } else {
                    editTxtName.text.clear()
                    editTxtPassword.text.clear()

                    (requireActivity() as? MainActivity)?.makeToast("Login Berhasil")
                    val intent = Intent(this@LoginFragment.requireActivity(), DashboardActivity::class.java)
                    startActivity(intent)
                }
            }

            txtToRegister.setOnClickListener{
                (requireActivity() as? MainActivity)?.switchFragment(0)
            }
        }

        return view
    }


}