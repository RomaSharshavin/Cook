package com.example.cookbooks

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cookbooks.databinding.ActivityMenuBinding
import java.io.IOException

class MenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val txtName = intent.getStringExtra("name")
        val img = intent.getIntExtra("img", 0)
        val photo = intent.getIntExtra("photo", 0)
        val txtRecipe = intent.getStringExtra("recipe") ?: ""

        val recipeData = loadRecipeFromAssets(txtRecipe)

        binding.mealName.text = txtName
        binding.textView.text = recipeData
        binding.imageViewMeal.setImageResource(photo)
        binding.IconMeal.setImageResource(img)
    }

    private fun loadRecipeFromAssets(fileName: String?): String? {
        if (fileName.isNullOrEmpty()) {
            return "Recipe file not found."
        }
        return try {
            assets.open(fileName).use { inputStream ->
                val size = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                String(buffer, Charsets.UTF_8)
            }
        } catch (e: IOException) {
            e.printStackTrace()
            "Error loading recipe: ${e.message}"
        }
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.textBack -> {
                startActivity(Intent(this, FoodActivity::class.java))
            }
        }
    }

}