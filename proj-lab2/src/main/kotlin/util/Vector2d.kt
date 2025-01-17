package util

import java.util.*
import kotlin.math.max
import kotlin.math.min

class Vector2d(
    val x: Int,
    val y: Int
) {

    fun upperRight(other: Vector2d): Vector2d = Vector2d(max(x, other.x), max(y, other.y))

    fun lowerLeft(other: Vector2d): Vector2d = Vector2d(min(x, other.x), min(y, other.y))

    operator fun compareTo(other : Vector2d): Int {
        if (this.x <= other.x && this.y <= other.y) return -1   // this precedes other
        if (this.x >= other.x && this.y >= other.y) return 1    // this follows other
        return 0    // neither vector precedes or follows the other
    }

    operator fun plus(other: Vector2d) : Vector2d = Vector2d(this.x + other.x, this.y + other.y)

    operator fun minus(other: Vector2d) : Vector2d = Vector2d(this.x - other.x, this.y - other.y)

    operator fun unaryMinus(): Vector2d = Vector2d(-this.x, -this.y)

    override fun toString(): String = "($x, $y)"

    override fun hashCode(): Int = Objects.hash(x, y)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (this.javaClass != other?.javaClass) return false
        other as Vector2d
        return x == other.x && y == other.y
    }
}