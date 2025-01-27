package model

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import util.Vector2d
import util.MapDirection

class BouncyMapTest : StringSpec({
    "canMoveTo() should return true for a position within map boundaries" {
        val map = BouncyMap(10, 10)

        map.canMoveTo(Vector2d(5, 6)) shouldBe true
    }

    "canMoveTo() should return false for a position outside the map boundaries" {
        val map = BouncyMap(10, 10)

        map.canMoveTo(Vector2d(15, 4)) shouldBe false
    }

    "place() should add an animal to the map" {
        val map = BouncyMap(10, 10)
        val animal = Animal(Vector2d(6, 7), map = map)

        map.place(animal) shouldBe true
        map.objectAt(Vector2d(6, 7)) shouldBe animal
    }

    "place() should update the animal's position if it's already on the map" {
        val map = BouncyMap(10, 10)
        val animal = Animal(Vector2d(2, 3), map = map)
        val newAnimalPosition = Vector2d(3, 4)

        map.place(animal) shouldBe true

        animal.position = newAnimalPosition

        map.place(animal) shouldBe true
        map.objectAt(newAnimalPosition) shouldBe animal
        map.objectAt(Vector2d(2, 3)) shouldBe null
    }

    "place() should find a new position for an animal if the current position is occupied" {
        val map = BouncyMap(10, 10)
        val animal1 = Animal(Vector2d(2, 3), map = map)
        val animal2 = Animal(Vector2d(2, 3), MapDirection.SOUTH, map)

        map.place(animal1)
        map.place(animal2)

        map.isOccupied(animal1.position) shouldBe true
        map.isOccupied(animal2.position) shouldBe true
        animal1.position shouldNotBe animal2.position
    }
})
