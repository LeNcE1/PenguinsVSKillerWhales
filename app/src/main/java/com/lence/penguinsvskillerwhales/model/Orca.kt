package com.lence.penguinsvskillerwhales.model

import android.graphics.Point

import com.lence.penguinsvskillerwhales.view.UpdatePresenter

class Orca(private val updatePresenter: UpdatePresenter) : Organism {
    private val lifeTime = 8
    private val limitHunger = 3

    override var moved: Boolean = false
    private var hunger = 0
    override var age = 0

    fun addHunger() {
        hunger++
    }

    fun eat() {
        hunger = 0
    }

    private fun addAge() {
        if (age < lifeTime) {
            age++
        } else {
            age = 1
        }
    }

    override fun lifeCycle(point: Point) {
        if ((!moved)) {
            if (hunger < limitHunger - 1) {
                if (age == lifeTime - 1) {
                    updatePresenter.breeding(point, Orca(updatePresenter))
                }
                updatePresenter.eat(point, this)
                addAge()
                moved = true
            } else {
                updatePresenter.death(point)

            }
        }
    }


}
