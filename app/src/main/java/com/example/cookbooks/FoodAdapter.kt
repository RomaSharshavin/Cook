package com.example.cookbooks

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class FoodAdapter(context: Context?, val layout: Int, val food: List<Food>)
    : ArrayAdapter<Food?>(context!!, layout, food as List<Food?>) {

    private val inflater: LayoutInflater
    init {
        inflater = LayoutInflater.from(context)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = inflater.inflate(layout, parent, false)
        val mealView: ImageView = view.findViewById(R.id.imageView_meal)
        val nameView: TextView = view.findViewById(R.id.textView_mealName)
        val food = food[position]
        mealView.setImageResource(food.getMealResource())
        nameView.text = food.getName()
        return view
    }
}
