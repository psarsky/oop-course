package agh.ics.oop.model;

import agh.ics.oop.model.util.Boundary;
import agh.ics.oop.model.util.IncorrectPositionException;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * The interface responsible for interacting with the map of the world.
 * Assumes that Vector2d and MoveDirection classes are defined.
 *
 * @author apohllo, idzik
 */
public interface WorldMap extends MoveValidator {

    /**
     * Place an animal on the map.
     *
     * @param animal The animal to place on the map.
     * The animal cannot be placed if the move is not valid.
     */
    void place(Animal animal) throws IncorrectPositionException;

    /**
     * Moves an animal (if it is present on the map) according to specified direction.
     * If the move is not possible, this method has no effect.
     */
    void move(Animal animal, MoveDirection direction);

    /**
     * Return true if given position on the map is occupied. Should not be
     * confused with canMove since there might be empty positions where the animal
     * cannot move.
     *
     * @param position Position to check.
     * @return True if the position is occupied.
     */
    boolean isOccupied(Vector2d position);

    /*
     * Verifies is the given position is within map boundaries.
     */
    boolean withinBoundaries(Vector2d position);

    /**
     * Return an animal at a given position.
     *
     * @param position The position of the animal.
     * @return animal or null if the position is not occupied.
     */
    Optional<WorldElement> objectAt(Vector2d position);

    /**
     * Return a list of all objects on the map.
     *
     * @return list of WorldElement objects.
     */
    List<WorldElement> getElements();

    /**
     *
     * Return current map boundaries.
     *
     * @return Boundary object.
     */
    Boundary getCurrentBounds();

    /**
     *
     * Returns the map's ID.
     *
     * @return UUID.
     */
    UUID getID();

    /**
     *
     * Returns a collection of all animals on the map, sorted by position.
     *
     * @return collection of animals.
     */
    Collection<Animal> getOrderedAnimals();
}

