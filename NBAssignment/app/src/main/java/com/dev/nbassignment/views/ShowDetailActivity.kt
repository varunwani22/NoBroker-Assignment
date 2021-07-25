package com.dev.nbassignment.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.dev.nbassignment.R
import kotlinx.android.synthetic.main.activity_show_detail.*

class ShowDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_detail)
        settingData()

        backpress.setOnClickListener {
            finish()
        }
    }

    private fun settingData() {
        val photo = intent.getStringExtra("image")
        val title = intent.getStringExtra("title")
        val subtitle = intent.getStringExtra("subtitle")
        Glide.with(ivDetails).load(photo).into(ivDetails)
        tvTitleDetails.text = title
        tvSubTitleDetails.text = subtitle
        heading.text = title
    }
}