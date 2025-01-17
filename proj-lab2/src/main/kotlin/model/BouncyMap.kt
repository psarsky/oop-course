package model

import util.Vector2d
import util.randomFreePosition
import util.randomPosition

class BouncyMap (
    private val height: Int = 0,
    private val width: Int = 0
): WorldMap {
    private val animals: HashMap<Vector2d, Animal> = HashMap()

    override fun canMoveTo(position: Vector2d): Boolean {
        return position < Vector2d(this.width, this.height)
                && position > Vector2d(0, 0)
    }

    override fun place(animal: Animal): Boolean {
        if (this.animals.containsValue(animal)) {
            for ((pos: Vector2d, value: Animal) in this.animals){
                if (value == animal){
                    this.animals.remove(pos)
                    break
                }
            }
        }
        if (!canMoveTo(animal.position)) {
            return false
        }
        if (!isOccupied(animal.position) && canMoveTo(animal.position)) {
            this.animals[animal.position] = animal
        } else {
            val randomFreePosition: Vector2d? = this.animals.randomFreePosition(Vector2d(this.width, this.height))
            if (randomFreePosition != null) {
                animal.position = randomFreePosition
                animals[randomFreePosition] = animal
            } else {
                val randomPosition: Vector2d? = this.animals.randomPosition()
                if (randomPosition != null) {
                    animal.position = randomPosition
                    this.animals[animal.position] = animal
                }
            }
        }
        return true
    }

    override fun isOccupied(position: Vector2d): Boolean {
        return animals.containsKey(position)
    }

    override fun objectAt(position: Vector2d): Any? {
        return animals[position]
    }
}