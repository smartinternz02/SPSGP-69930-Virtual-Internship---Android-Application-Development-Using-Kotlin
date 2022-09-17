package ghanam.com.gsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import ghanam.com.gsapp.databinding.FragmentLoginBinding
import ghanam.com.gsapp.singletons.VirtualDB


class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        VirtualDB.toString()
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.loginButton.setOnClickListener {
            validateLogin(it)
        }
        binding.singUpLink.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_loginFragment_to_signUpFragment)
        }
        return binding.root
    }



    private fun validateLogin(it: View?){
        binding.apply {
            val email = emailTextInput.text.toString()
            val password = emailTextInput.text.toString()

            if (email.isEmpty() || password.isEmpty()){
                Toast.makeText(context,"please complete all fields",Toast.LENGTH_LONG).show()
                return
            }
            if (it != null) {
                Navigation.findNavController(it).navigate(R.id.action_loginFragment_to_homeFragment)
            }

        }
    }



}