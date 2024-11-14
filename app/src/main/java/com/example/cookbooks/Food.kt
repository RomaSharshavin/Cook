package com.example.cookbooks

class Food(private var name: String?, private var mealResource: Int,
           private var mealPhoto: Int, private var mealRecipe: String?) {

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getMealResource(): Int {
        return mealResource
    }

    fun setMealResource(mealResource: Int) {
        this.mealResource = mealResource
    }

    fun getMealPhoto(): Int {
        return mealPhoto
    }

    fun setMealPhoto(mealPhoto: Int) {
        this.mealPhoto = mealPhoto
    }

    fun getMealRecipe(): String? {
        return mealRecipe
    }

    fun setMealRecipe(mealRecipe: String?) {
        this.mealRecipe = mealRecipe
    }
}
