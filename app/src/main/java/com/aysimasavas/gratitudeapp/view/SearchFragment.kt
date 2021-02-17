package com.aysimasavas.gratitudeapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnCloseListener
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aysimasavas.gratitudeapp.R
import com.aysimasavas.gratitudeapp.adapter.RecyclerAdapter
import com.aysimasavas.gratitudeapp.helpers.DateFormatHelper
import com.aysimasavas.gratitudeapp.model.NoteModel
import com.aysimasavas.gratitudeapp.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_search.*
import java.util.*
import kotlin.collections.ArrayList

class SearchFragment : Fragment() ,RecyclerAdapter.Listener {

    private lateinit var noteViewModel: NoteViewModel

    private var noteModels:ArrayList<NoteModel>?=null
    private var recyclerAdapter: RecyclerAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager: RecyclerView.LayoutManager= LinearLayoutManager(this.context)

        recyclerSearchView.layoutManager=layoutManager

        noteModels= ArrayList()

        recyclerAdapter=RecyclerAdapter(noteModels!!,this@SearchFragment)


        recyclerSearchView.adapter=recyclerAdapter


        noteViewModel=ViewModelProviders.of(this).get(NoteViewModel::class.java)

        searchView.isSubmitButtonEnabled=true
        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener, android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(query !=null)
                {
                    getItemsFromDb(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    getItemsFromDb(newText)
                }
                return true
            }

        })

        searchView.setOnCloseListener(object: OnCloseListener, android.widget.SearchView.OnCloseListener{
            override fun onClose(): Boolean {

                recyclerAdapter!!.notifyDataSetChanged()
                noteModels!!.clear()
                recyclerAdapter!!.notifyDataSetChanged()

                return true

            }
        })

    }



    private fun getItemsFromDb(searchText: String) {
        var searchText = searchText
        searchText = "%$searchText%"


        val list=noteViewModel.searchForItem(note = searchText)


        Log.i("finnd ",list.toString())
        Log.i("search ",searchText)



        noteModels?.clear()
        noteViewModel.searchForItem(note = searchText)?.let { noteModels?.addAll(it) }

        recyclerAdapter?.notifyDataSetChanged()


    }

    override fun onItemClick(noteModel: NoteModel, view: View) {
        val mArgs= noteModel.date?.let { DateFormatHelper().stringToCalendar(it) }

        val action= noteModel.note?.let {
            SearchFragmentDirections.actionSearchFragmentToNoteFragment(mArgs!!.get(Calendar.DAY_OF_MONTH),
                    mArgs.get(Calendar.MONTH),
                    mArgs.get(Calendar.YEAR),"old", it)
        }

        action?.let { Navigation.findNavController(view).navigate(it) }
    }


}

