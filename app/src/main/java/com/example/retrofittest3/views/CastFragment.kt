package com.example.retrofittest3.views

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.cast_menu,menu)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        castViewModel = ViewModelProvider(this).get(CastViewModel::class.java)

        if (arguments != null){
          movieId = arguments!!.getInt("movieId")
            Log.i("tag-CastFragment", "movie im neuen Fragment $movieId")
        }

        castViewModel.getCast(movieId)

        castViewModel.actorList.observe(this, Observer{
                castList -> initViewPager(castList)
        })
    }

    private fun initViewPager(castList: List<Cast>){
        val adapter = ViewPagerAdapter(castList, {cast : Cast -> actorClicked(cast)})
        viewPager.adapter = adapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cast, container, false)
    }

    private fun actorClicked(cast : Cast){
        Log.i("Tag", "Another clickListener seems to be responding")
        val fragment = MoviesByActorFragment.newInstance(cast.id)
        replaceFragment(fragment)
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = activity!!.supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.root_layout, fragment)
        transaction.commit()
    }
}
