package com.example.pertemuan11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.pertemuan11.databinding.ActivityMainBinding
import com.example.pertemuan11.model.PostResponse
import com.example.pertemuan11.retrofit.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        loadRandomCatImage()

        binding.btnRandom.setOnClickListener {
            loadRandomCatImage()
        }
    }

    private fun loadRandomCatImage() {
        val response = ApiClient.instance.getcats()

        response.enqueue(object : Callback<ArrayList<PostResponse>> {
            override fun onResponse(
                call: Call<ArrayList<PostResponse>>,
                response: Response<ArrayList<PostResponse>>
            ) {
                response.body()?.let {
                    if (it.isNotEmpty()) {
                        val url = it[0].url

                        Glide.with(this@MainActivity)
                            .load(url)
                            .into(binding.imgCat)
                    }
                }
            }

            override fun onFailure(call: Call<ArrayList<PostResponse>>, t: Throwable) {
                //! Handle failure
            }
        })
    }

}