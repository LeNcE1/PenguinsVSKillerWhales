package com.lence.penguinsvskillerwhales.model

import android.graphics.Point

import com.lence.penguinsvskillerwhales.view.UpdatePresenter

class Penguin(private val updatePresenter: UpdatePresenter) : Organism {
    override var moved = false
//        set(value: Boolean) {
//            moved = value
//        }
    override var age = 0

    private val lifeTime = 3

    private fun addAge() {
        if (age < lifeTime) {
            age++
        } else {
            age = 1
        }
    }

    override fun lifeCycle(point: Point) {
        if ((!moved)) {
            if (age == lifeTime - 1) {
                updatePresenter.breeding(point, Penguin(updatePresenter))
            }
            updatePresenter.move(point)
            addAge()
            moved = true
        }
    }

}
