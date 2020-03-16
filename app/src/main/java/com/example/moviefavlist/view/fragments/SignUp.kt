package com.example.moviefavlist.view.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.moviefavlist.interfaces.AuthModel
import com.example.moviefavlist.interfaces.AuthState
import com.example.moviefavlist.interfaces.LoginState
import com.example.moviefavlist.R
import com.example.moviefavlist.viewmodel.SignUpViewModel
import kotlinx.android.synthetic.main.sign_up_fragment.*


class SignUp : Fragment(), AuthModel {

    companion object {
        fun newInstance() = SignUp()
        var navController: NavController? = null

    }

    private lateinit var viewModel: SignUpViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.sign_up_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SignUpViewModel::class.java)
        // TODO: Use the ViewModel

        Signup.setOnClickListener(View.OnClickListener {
            if(name_Signup.text.toString().equals("") || password_Signup.text.toString().equals("") || Email_Signup.text.toString().equals("") || phonenumber_Signup.text.toString().equals("")){
                Toast.makeText(requireContext(),"fill all the fields",Toast.LENGTH_LONG).show()
            }
            else {
                viewModel.Create_User(Email_Signup.text.toString(), password_Signup.text.toString(),phonenumber_Signup.text.toString(), name_Signup.text.toString())

            }
        })
    }


    // call back for sign up
    override fun onResultRequest(state: AuthState) {
        when (state) {
            AuthState.SUCCESS -> onSuccessAuth()
            AuthState.FAILED -> showMessage(state)
            AuthState.EXCEPTION -> showMessage(state)
        }
    }

    override fun onResultLoggedinState(state: LoginState) {
        TODO("Not yet implemented")
    }

    private fun onSuccessAuth() {
        //Toast.makeText(activity, "success", Toast.LENGTH_SHORT).show()
        System.out.println("success lol")
        navController!!.navigate(R.id.action_signUp_to_second)


    }

    private fun showMessage(authState: AuthState){
        //Toast.makeText(activity, authState.msg, Toast.LENGTH_SHORT).show()
        System.out.println("failed lol")

    }
}
