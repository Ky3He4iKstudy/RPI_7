package dev.ky3he4ik.pract31_32

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dev.ky3he4ik.pract31_32.databinding.ActivityGuessBinding

class GuessActivity : AppCompatActivity() {
    lateinit var binding: ActivityGuessBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuessBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var lowerBound = intent.getIntExtra(LOWER_BOUND_KEY, Int.MIN_VALUE)
        var upperBound = intent.getIntExtra(UPPER_BOUND_KEY, Int.MIN_VALUE)
        if (lowerBound == Int.MIN_VALUE || upperBound == Int.MIN_VALUE) {
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show()
            super.onBackPressed()
        }

        var currentGuess = getGuess(lowerBound, upperBound)
        binding.lessOrEqual.setOnClickListener {
            lowerBound = currentGuess
            currentGuess = getGuess(lowerBound, upperBound)
            if (lowerBound == currentGuess) {
                Toast.makeText(this, "Finally: $currentGuess", Toast.LENGTH_LONG).show()
                super.onBackPressed()
            } else
                binding.guessText.text = "Number is \n$currentGuess"
        }
        binding.more.setOnClickListener {
            upperBound = currentGuess
            currentGuess = getGuess(lowerBound, upperBound)
            binding.guessText.text = "Number is \n$currentGuess"
        }
        binding.guessText.text = "Number is \n$currentGuess"
    }

    private fun getGuess(lower: Int, upper: Int) = (upper - lower) / 2 + lower

    companion object {
        val LOWER_BOUND_KEY = "lower_bound"
        val UPPER_BOUND_KEY = "upper_bound"
    }
}
