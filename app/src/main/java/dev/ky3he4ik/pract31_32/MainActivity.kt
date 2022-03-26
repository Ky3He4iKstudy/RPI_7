package dev.ky3he4ik.pract31_32

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import dev.ky3he4ik.pract31_32.databinding.ActivityGuessBinding
import dev.ky3he4ik.pract31_32.databinding.ActivityMainBinding
import kotlin.reflect.KProperty1

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startButton.setOnClickListener {
            val lower = binding.lowerBound.text.toString().toIntOrNull()
            val upper = binding.upperBound.text.toString().toIntOrNull()
            if (lower == null || upper == null || lower >= upper) {
                Toast.makeText(this, "Invalid data", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(this, GuessActivity::class.java)
            intent.putExtra(GuessActivity.LOWER_BOUND_KEY, lower)
            intent.putExtra(GuessActivity.UPPER_BOUND_KEY, upper)
            startActivity(intent)
        }
    }
}
