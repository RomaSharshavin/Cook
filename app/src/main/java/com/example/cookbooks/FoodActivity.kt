package com.example.cookbooks

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cookbooks.databinding.ActivityFoodBinding
import java.io.FileNotFoundException

class FoodActivity : AppCompatActivity() {
    private val meal = ArrayList<Food>()
    private lateinit var binding: ActivityFoodBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        setInitialData()

        val mealAdapter = FoodAdapter(this, R.layout.list_item, meal)
        binding.mealList.adapter = mealAdapter

        binding.mealList.onItemClickListener = AdapterView.OnItemClickListener { parent, _, position, _ ->
            val selectedMeal = parent.getItemAtPosition(position) as? Food
            selectedMeal?.let {
                openMenu(it)
            }
        }
    }

    private fun setInitialData() {
        meal.apply {
            add(Food("Борщ", R.drawable.ic_borsch, R.drawable.borsch, loadRecipe(R.raw.borsch)))
            add(Food("Цезарь", R.drawable.ic_cesar, R.drawable.cesar, loadRecipe(R.raw.cesar)))
            add(Food("Блинчики", R.drawable.ic_blinchik, R.drawable.blinchik, loadRecipe(R.raw.blinchik)))
            add(Food("Мол. коктейль", R.drawable.ic_milkshake, R.drawable.milkshake, loadRecipe(R.raw.milkshake)))
            add(Food("Спагетти", R.drawable.ic_spagetti, R.drawable.spagetti, loadRecipe(R.raw.spagetti)))
        }
    }

    private fun loadRecipe(resourceId: Int): String? {
        return try {
            val inputStream = resources.openRawResource(resourceId)
            inputStream.bufferedReader().use { it.readText() }
        } catch (e: FileNotFoundException) {
            Log.e("FoodActivity", "Файл не найден для идентификатора ресурса: $resourceId", e)
            null
        } catch (e: Exception) {
            Log.e("FoodActivity", "Ошибка при загрузке рецепта: ${e.message}", e)
            null
        }
    }


    private fun openMenu(meal: Food) {
        val intent = Intent(this, MenuActivity::class.java).apply {
            putExtra("name", meal.getName())
            putExtra("img", meal.getMealResource())
            putExtra("photo", meal.getMealPhoto())
            putExtra("recipe", meal.getMealRecipe())
        }
        startActivity(intent)
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.textBack -> {
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
    }

}