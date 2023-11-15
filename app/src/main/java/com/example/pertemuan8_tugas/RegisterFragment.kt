package com.example.pertemuan8_tugas

import android.content.Intent
import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.pertemuan8_tugas.databinding.FragmentRegisterBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RegisterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterFragment : Fragment(R.layout.fragment_register) {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private var _binding:  FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    companion object {
        var username: String? = "Admin"
        var password: String? = "Admin"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        val view = binding.root
        val checkBox = binding.checkBox
        val tac = "<font color=#FF000000>By checking the box you agree to our</font> " +
                "<font color=#525BFF>Terms</font> " +
                "<font color=#FF000000>and</font> " +
                "<font color=#525BFF>Conditions.</font>"
        checkBox.text = Html.fromHtml(tac)

        with(binding) {

            btnRegister.setOnClickListener{
                if(editTxtName.text!!.isEmpty()) {
                    editTxtName.error = "Mohon Isi Username"
                } else if(editTxtEmail.text!!.isEmpty()) {
                    editTxtEmail.error = "Mohon Isi Email"
                } else if(editTxtPhone.text!!.isEmpty()) {
                    editTxtPhone.error = "Mohon Isi Nomor Telepon Anda"
                } else if(editTxtPassword.text!!.isEmpty()) {
                    editTxtPassword.error = "Mohon Isi Password"
                } else if(!checkBox.isChecked) {
                    (requireActivity() as? MainActivity)?.makeToast("Mohon Setujui Syarat dan Ketentuan")
                } else {
                    username = editTxtName.text.toString()
                    password = editTxtPassword.text.toString()

                    editTxtName.text.clear()
                    editTxtEmail.text.clear()
                    editTxtPhone.text.clear()
                    editTxtPassword.text.clear()

                    (requireActivity() as? MainActivity)?.makeToast("Akun Berhasil Terbuat")
                    val intent = Intent(this@RegisterFragment.requireActivity(), DashboardActivity::class.java)
                    startActivity(intent)
                }
            }

            txtToLogin.setOnClickListener{
                (requireActivity() as? MainActivity)?.switchFragment(1)
            }
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}