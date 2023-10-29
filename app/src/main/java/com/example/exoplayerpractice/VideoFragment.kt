package com.example.exoplayerpractice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.example.exoplayerpractice.databinding.FragmentVideoBinding

/**
 * 影片播放頁
 */
class VideoFragment : Fragment() {

    private var _binding: FragmentVideoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var mPlayer:ExoPlayer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVideoBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val url = arguments?.getString(VIDEO_URL)?:return
        val mediaItem = MediaItem.fromUri(url)
        mPlayer = ExoPlayer.Builder(requireContext()).build()
        binding.exoPlayer.player = mPlayer

        mPlayer.apply {
            addMediaItem(mediaItem)
            prepare()
            playWhenReady = true
        }

//        binding.buttonSecond.setOnClickListener {
//            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
//        }
    }

    override fun onPause() {
        super.onPause()
        mPlayer.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPlayer.release()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object{
        private const val VIDEO_URL = "video_url"
        fun newInstance(videoUrl:String) = VideoFragment().apply {
            arguments = bundleOf(
                VIDEO_URL to videoUrl
            )
        }
    }
}