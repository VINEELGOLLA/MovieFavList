package com.example.moviefavlist.view.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.moviefavlist.R
import com.example.moviefavlist.interfaces.UserModel
import com.example.moviefavlist.model.user
import com.example.moviefavlist.viewmodel.UserDetailViewModel
import kotlinx.android.synthetic.main.user_detail_fragment.*


class UserDetail : Fragment(), UserModel {

    companion object {
        fun newInstance() = UserDetail()
        var navController: NavController? = null
        lateinit var name: TextView
        //lateinit var phonenumber: TextView

    }

    private lateinit var viewModel: UserDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.user_detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        name = view.findViewById(R.id.name_user_detail)
        //phonenumber = view.findViewById(R.id.phonenumber_user_detail)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(UserDetailViewModel::class.java)

        viewModel.downuserdetail()

        signout_user_detail.setOnClickListener(View.OnClickListener {
            viewModel.signout()
            navController!!.navigate(R.id.action_userDetail_to_mainActivity)

        })

    }

    override fun onResultRequestS(user: user) {
        System.out.println("main"+user)
        name.setText("Welcome back " + user.username)
        //phonenumber.setText(user.phonenumber)
    }

    override fun onResultRequestF(msg: String) {
        TODO("Not yet implemented")
    }
}
