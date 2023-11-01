package com.example.exoplayerpractice.videopage

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exoplayerpractice.R
import com.example.exoplayerpractice.databinding.ActivityVideosBinding

class VideosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVideosBinding

    private val videoUrls = listOf(
        "https://storage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
        "https://storage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
        "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4",
        "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4",
        "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4",
    )

    private val adapter by lazy{
        VideoAdapter(videoUrls)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityVideosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar))
        binding.toolbarLayout.title = title
        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        binding.contentInclude.videoRecyclerview?.run {
            adapter = this@VideosActivity.adapter
            layoutManager = LinearLayoutManager(this@VideosActivity)
        }
    }

    companion object{
        fun getIntent(context: Context) = Intent(context, VideosActivity::class.java)
    }
}