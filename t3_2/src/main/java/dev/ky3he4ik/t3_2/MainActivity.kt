package dev.ky3he4ik.t3_2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dev.ky3he4ik.t3_2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val regUrl = Regex("https?://.+\\..+")
        val regPhone = Regex("\\+?\\d{11}")
        val regMap = Regex("(?<lat>\\d+\\.\\d+), ?(?<long>\\d+\\.\\d+)")
        binding.launch.setOnClickListener {
            when {
                binding.radio.checkedRadioButtonId in listOf(
                    binding.browser.id,
                    binding.auto.id
                ) && regUrl.matches(binding.argument.text) -> {
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(binding.argument.text.toString())))
                }
                binding.radio.checkedRadioButtonId in listOf(
                    binding.phone.id,
                    binding.auto.id
                ) && regPhone.matches(binding.argument.text) -> {
                    startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel: ${binding.argument.text}")))
                }
                binding.radio.checkedRadioButtonId in listOf(
                    binding.map.id,
                    binding.auto.id
                ) && regMap.matches(binding.argument.text) -> {
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("geo: ${binding.argument.text}")))
                }
                else ->
                    Toast.makeText(this, "Invalid argument", Toast.LENGTH_SHORT).show()
            }
        }
    }
}