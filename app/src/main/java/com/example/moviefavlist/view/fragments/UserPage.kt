package com.example.moviefavlist.view.fragments

import android.app.AlertDialog
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviefavlist.R
import com.example.moviefavlist.viewmodel.UserPageViewModel
import com.example.moviekotlinmvvm.movielistAdapter
import com.example.moviekotlinmvvm.pojo.movieslist


class UserPage : Fragment(), movielistAdapter.ClickListener {

    private lateinit var searchView: SearchView
    private lateinit var listView: ListView

    lateinit var recyclerView: RecyclerView
    lateinit var dialogBuilder: AlertDialog.Builder



    var name: CharSequence? = null


    companion object {
        fun newInstance(): UserPage{
            return UserPage()

        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchView = view.findViewById(R.id.search)
        listView = view.findViewById<ListView>(R.id.listview)

        recyclerView = view.findViewById(R.id.recyclerview)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.cancelJobs()
    }



    private lateinit var viewModel: UserPageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.user_page_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(UserPageViewModel::class.java)

        dialogBuilder = AlertDialog.Builder(requireContext())



        searchView.isSubmitButtonEnabled=true
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                println("onQueryTextSubmit called")
                listView.visibility = View.INVISIBLE
                listView.isEnabled = false
                listView.isClickable = false

                if (p0 != null) {
                    println("on text inside" +p0)
                    viewModel.getquery(p0)
                    viewModel.newsLiveData1.observe(viewLifecycleOwner, Observer {
                        System.out.println(it.results)

                        //recyclerView = view!!.findViewById(R.id.recyclerview)
                        recyclerView.layoutManager = LinearLayoutManager(activity)
                        recyclerView.adapter = movielistAdapter(it.results!!, this@UserPage)
                    })
                }

                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })
    }


    // adapter callback for itemclick to add the movie in favourite list
    override fun itemclicked(task: movieslist) {
        //System.out.println(task.original_title)
        dialogBuilder.setMessage("Do you want to add " + task.original_title )
            .setCancelable(false)

        dialogBuilder.setPositiveButton("YES"){ dialog, which ->
            viewModel.uploadfav(task)

        }

        dialogBuilder.setNegativeButton("No"){ dialog, which ->
        }

        val alert = dialogBuilder.create()
        alert.show()
        //viewModel.uploadfav(task)
    }

}
