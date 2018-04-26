package com.lence.penguinsvskillerwhales.view

import android.graphics.Point
import com.lence.penguinsvskillerwhales.model.Organism
import com.lence.penguinsvskillerwhales.utils.Constants
import java.util.*

class Field(private val height: Int, private val width: Int ) {
    private var mLists: MutableList<Organism?> = Collections.synchronizedList(ArrayList(height * width))
    operator fun set(x: Int, y: Int, organism: Organism?) {
        mLists[y * width + x] = organism
    }

    operator fun set(thisPoint: Point, organism: Organism?) {
        mLists[thisPoint.y * width + thisPoint.x] = organism
    }

    operator fun get(x: Int, y: Int): Organism? {
        //Log.e("get", x.toString() + " " + y)
        return mLists[y * width + x]
    }

    operator fun get(thisPoint: Point): Organism? {
        return mLists[thisPoint.y * width + thisPoint.x]
    }

    fun isInField(x: Int, y: Int): Boolean {
        return x in 0..(width - 1) && y in 0..(height-1)
    }

    fun add(organism: Organism?) {
        mLists.add(organism)
    }
    companion object {
        @JvmStatic
        fun getPointByDirection(x: Int, y: Int, direction: Int): Point {
            when (direction) {
                Constants.left -> {
                    return Point(x - 1, y)
                }
                Constants.right -> {
                    return Point(x + 1, y)
                }
                Constants.up -> {
                    return Point(x, y - 1)
                }
                Constants.down -> {
                    return Point(x, y + 1)
                }
                Constants.left_up -> {
                    return Point(x - 1, y - 1)
                }
                Constants.right_up -> {
                    return Point(x + 1, y - 1)
                }
                Constants.left_down -> {
                    return Point(x - 1, y + 1)
                }
                Constants.right_down -> {
                    return Point(x + 1, y + 1)
                }
            }
            return Point(x, y)
        }
    }

    fun size(): Int {
        return width * height
    }

}