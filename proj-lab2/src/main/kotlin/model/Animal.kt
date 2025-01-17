package model

import util.MapDirection
import util.MoveDirection
import util.Vector2d

class Animal(
    var position: Vector2d,
    private var direction: MapDirection = MapDirection.NORTH,
    private var map: WorldMap
) {
    fun move(direction: MoveDirection) {
        var newPosition: Vector2d = this.position
        when (direction) {
            MoveDirection.RIGHT -> this.direction = this.direction.next()
            MoveDirection.LEFT -> this.direction = this.direction.previous()
            MoveDirection.FORWARD -> newPosition = this.position + this.direction.toUnitVector()
            MoveDirection.BACKWARD -> newPosition = this.position - this.direction.toUnitVector()
        }
        if (newPosition !== this.position && this.map.canMoveTo(newPosition)) {
            this.position = newPosition
        }
    }

    fun isAt(position: Vector2d): Boolean {
        return this.position == position
    }

    override fun toString(): String = this.direction.toString()
}