package com.techmaster.interviewassignment.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.techmaster.interviewassignment.R
import com.techmaster.interviewassignment.data.PostModels
import com.techmaster.interviewassignment.databinding.ActivityDetailScreenBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailScreen : AppCompatActivity() {
    private lateinit var data:PostModels
    private lateinit var binding:ActivityDetailScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras
        if (extras != null) {
            data = (intent.getSerializableExtra("itemData") as PostModels?)!! //Obtaining data

            binding.apply {
                title.text = data.title
                userId.text =
                    binding.userId.context.getString(R.string.user_id, data.userId.toString())
                postid.text = binding.userId.context.getString(R.string.post_id, data.id.toString())
                body.text = data.body
            }

        }

        binding.back.setOnClickListener {
            finish()
        }


    }
}
