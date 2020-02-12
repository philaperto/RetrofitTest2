package com.example.retrofittest3.views

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2

import com.example.retrofittest3.R
import com.example.retrofittest3.database.Cast
import kotlinx.android.synthetic.main.fragment_cast.*


class CastFragment : Fragment() {

    private lateinit var castViewModel: CastViewModel
    var  movieId = 0

    companion object {


        fun newInstance(movieId: Int): CastFragment {
            val fragment = CastFragment()
            val bundle = Bundle()
            bundle.putInt("movieId", movieId)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        castViewModel = ViewModelProvider(this).get(CastViewModel::class.java)
        if (arguments != null){
          movieId = arguments!!.getInt("movieId")
            Log.i("tag", "movie im neuen Fragment $movieId")
        }
        castViewModel.getCast(movieId)

        castViewModel.actorList.observe(this, Observer{
                castList -> initviewPager(castList)
        })

    }

    private fun initviewPager(castList: List<Cast>){
        val adapter = ViewPagerAdapter(castList)
        viewPager.adapter = adapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cast, container, false)
    }




}
