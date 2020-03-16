package com.example.moviefavlist.view.fragments

import android.app.AlertDialog
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviefavlist.interfaces.MoviefavList
import com.example.moviefavlist.viewmodel.FavlistViewModel
import com.example.moviefavlist.R
import com.example.moviekotlinmvvm.movielistAdapter
import com.example.moviekotlinmvvm.pojo.movieslist


class favlist : Fragment(), MoviefavList,movielistAdapter.ClickListener  {

    companion object {
        fun newInstance() = favlist()
        lateinit var recyclerView: RecyclerView

        private lateinit var viewModel: FavlistViewModel
        private lateinit var dialogBuilder: AlertDialog.Builder


    }


    //lateinit var recyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.favlist_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerview)



    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FavlistViewModel::class.java)

        viewModel.downloadfavlist()

        dialogBuilder = AlertDialog.Builder(requireContext())






    }

    override fun onResultRequestS(favlist: ArrayList<movieslist>) {
        System.out.println("lololol")

        System.out.println(favlist)

        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = movielistAdapter(favlist, this)

    }

    override fun onResultRequestF(msg: String) {
        TODO("Not yet implemented")
    }


    // adapter callback for itemclick to delete the movie from favourite list
    override fun itemclicked(task: movieslist) {
        System.out.println(task)


        dialogBuilder.setMessage("Do you want to delete " + task.original_title )
            .setCancelable(false)

        dialogBuilder.setPositiveButton("YES"){dialog, which ->
            viewModel.delete(task.id!!)
            }

        dialogBuilder.setNegativeButton("No"){dialog,which ->
        }

        val alert = dialogBuilder.create()
        alert.show()


        //viewModel.delete(task.id!!)
    }



}
