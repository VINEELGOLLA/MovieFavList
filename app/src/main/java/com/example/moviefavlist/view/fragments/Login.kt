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
import androidx.navigation.findNavController
import com.example.moviefavlist.interfaces.AuthModel
import com.example.moviefavlist.interfaces.AuthState
import com.example.moviefavlist.interfaces.LoginState
import com.example.moviefavlist.R
import com.example.moviefavlist.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.login_fragment.*


class Login : Fragment(), AuthModel {

    companion object {
        fun newInstance() = Login()
        var navController: NavController? = null

    }
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //System.out.println("callled")
        navController = Navigation.findNavController(view)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        // check the user is logged in or not on app start
        viewModel.check()


        // new user register
        register.setOnClickListener(View.OnClickListener {
            it.findNavController().navigate(R.id.action_login_to_signUp2)
        })


        // login
        login.setOnClickListener(View.OnClickListener {
            if(Email.text.toString().equals("") || password.text.toString().equals("")){
                Toast.makeText(requireContext(),"fill all the fields", Toast.LENGTH_LONG).show()
            }
            else{
                viewModel.login(Email.text.toString(), password.text.toString())
            }
        })
    }
    // callback for after clicking login button
    override fun onResultRequest(state: AuthState) {
        when (state) {
            AuthState.SUCCESS -> onSuccessAuth()
            AuthState.FAILED -> showMessage(state)
            AuthState.EXCEPTION -> showMessage(state)
        }
    }


    // callback for user is logged in or not on app start
    override fun onResultLoggedinState(state: LoginState) {
        when (state) {
            LoginState.Loggedin -> onSuccessLogin()
            LoginState.NotLogginin -> onFailedLogout()
        }
    }

    private fun onSuccessAuth() {
        //Toast.makeText(activity, "success", Toast.LENGTH_SHORT).show()
        System.out.println("success lol")
        navController!!.navigate(
            R.id.action_login_to_second
        )

    }

    private fun showMessage(authState: AuthState){
        //Toast.makeText(activity, authState.msg, Toast.LENGTH_SHORT).show()
        System.out.println("failed lol")
    }

    private fun onSuccessLogin(){
        System.out.println("logged in")
        //view.findNavController().navigate(R.id.action_login_to_userPage)
        navController!!.navigate(
            R.id.action_login_to_second
        )
    }

    private fun onFailedLogout(){
        System.out.println("not logged in")
    }
}
