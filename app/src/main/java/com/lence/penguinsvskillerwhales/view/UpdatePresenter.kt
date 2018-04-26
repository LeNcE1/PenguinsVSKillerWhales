package com.lence.penguinsvskillerwhales.view

import android.graphics.Point

import com.lence.penguinsvskillerwhales.model.Orca
import com.lence.penguinsvskillerwhales.model.Organism

interface UpdatePresenter {
    fun update(list: Field)
    fun move(thisPoint: Point)
    fun breeding(thisPoint: Point, organism: Organism)
    fun eat(thisPoint: Point, orca: Orca)
    fun death(thisPoint: Point)
}
