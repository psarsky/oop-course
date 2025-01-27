package model

import util.Vector2d

interface WorldMap {
    /**
     * Indicate if any object can move to the given position.
     *
     * @param position The position checked for the movement possibility.
     * @return True if the object can move to that position.
     */
    fun canMoveTo(position: Vector2d): Boolean

    /**
     * Place an animal on the map.
     *
     * @param animal The animal to place on the map.
     * The animal cannot be placed if the move is not valid.
     */
    fun place(animal: Animal): Boolean

    /**
     * Return true if given position on the map is occupied. Should not be
     * confused with canMove since there might be empty positions where the animal
     * cannot move.
     *
     * @param position Position to check.
     * @return True if the position is occupied.
     */
    fun isOccupied(position: Vector2d): Boolean

    /**
     * Return an animal at a given position.
     *
     * @param position The position of the animal.
     * @return animal or null if the position is not occupied.
     */
    fun objectAt(position: Vector2d): Any?
}