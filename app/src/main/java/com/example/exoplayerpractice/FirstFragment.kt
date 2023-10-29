package com.example.exoplayerpractice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.exoplayerpractice.databinding.FragmentFirstBinding
import com.example.exoplayerpractice.databinding.FragmentTabCircleBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private lateinit var tabLayoutMediator: TabLayoutMediator

    private val videoUrls = listOf(
        "https://storage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
        "https://storage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
        "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4",
        "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4",
        "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4",
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tabLayoutMediator = TabLayoutMediator(
            binding.videoTablayout, binding.videoViewpager
        ) { tab, index ->
            tab.customView = FragmentTabCircleBinding.inflate(layoutInflater).root
        }
        binding.videoViewpager.adapter =
            ScreenSlidePagerAdapter(childFragmentManager, lifecycle, videoUrls)
        binding.videoTablayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val tabCircle = tab?.customView?.findViewById<CheckBox>(R.id.circle_checkbox)
                tabCircle?.isChecked = true
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val tabCircle = tab?.customView?.findViewById<CheckBox>(R.id.circle_checkbox)
                tabCircle?.isChecked = false
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
        tabLayoutMediator.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        tabLayoutMediator.detach()
        _binding = null
    }
}

private class ScreenSlidePagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    private val videoUrls: List<String>
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount() = videoUrls.size
    override fun createFragment(position: Int) = VideoFragment.newInstance(videoUrls[position])
}