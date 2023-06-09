package com.matches_simulator_app.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.matches_simulator_app.databinding.ActivityDetailBinding
import com.matches_simulator_app.domain.Match

class DetailActivity : AppCompatActivity() {
    val db = Firebase.firestore
    object Extras {
        const val MATCH = "EXTRA_MATCH"
    }

    lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        setupDadosMatch()
    }

    private fun setupDadosMatch() {
        intent?.extras?.getParcelable<Match>(Extras.MATCH)?.let { match ->
            supportActionBar?.title = match.place.name

            binding.tvHomeTeamName.text = match.homeTeam.name
            binding.tvAwayTeamName.text = match.awayTeam.name

            binding.tvDescription.text = match.description

            //Set Imagem Times

            Glide.with(this).load(match.homeTeam.image).into(binding.ivHomeTeam)
            Glide.with(this).load(match.awayTeam.image).into(binding.ivAwayTeam)
            Glide.with(this).load(match.place.image).into(binding.ivPlace)




            binding.rbHomeTeamStars.rating = match.homeTeam.force.toFloat()
            binding.rbAwayTeamStars.rating = match.awayTeam.force.toFloat()

            if (match.homeTeam.score != -1){
                //Set placar Match
                binding.tvHomeTeamScore.text = match.homeTeam.score.toString()
                binding.tvAwayTeamScore.text = match.awayTeam.score.toString()
            }


        }
    }
}