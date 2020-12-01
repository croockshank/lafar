package com.genadidharma.lafar.ui.restaurant.sections.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.genadidharma.lafar.data.Menu
import com.genadidharma.lafar.databinding.FragmentMenuDetailBinding

class MenuDetailFragment : Fragment() {
    private val args: MenuDetailFragmentArgs by navArgs()
    private val menu: Menu by lazy(LazyThreadSafetyMode.NONE) { args.menu }

    private lateinit var binding: FragmentMenuDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMenuDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}