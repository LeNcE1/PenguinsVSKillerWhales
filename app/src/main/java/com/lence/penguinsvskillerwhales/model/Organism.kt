package com.lence.penguinsvskillerwhales.model

import android.graphics.Point

interface Organism {
    var age: Int
    var moved: Boolean
    fun lifeCycle(point: Point)

}
