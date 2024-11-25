package agh.ics.oop;

import agh.ics.oop.model.*;
import agh.ics.oop.model.util.IncorrectPositionException;

import java.util.ArrayList;
import java.util.List;

public class Simulation {

    private final List<Animal> animals;
    private final List<MoveDirection> moves;
    private final WorldMap map;

    public Simulation(List<Vector2d> initialPositions, List<MoveDirection> moves, WorldMap map) {

        this.moves = moves;
        this.map = map;
        this.animals = new ArrayList<>();

        for (Vector2d pos : initialPositions) {
            try {
                Animal animal = new Animal(pos);
                if (this.map.place(animal)) {
                    animals.add(animal);
                }
            } catch (IncorrectPositionException e) {
                System.out.println("IncorrectPositionException: " + e.getMessage());
            }
        }
    }

    @Override
    public String toString() {
        return map.toString();
    }

    public void run() {

        int animalIndex = 0;
        int animalCount = animals.size();

        for (MoveDirection move : moves) {
            if (animalIndex > animalCount - 1) {
                break;
            }
            map.move(animals.get(animalIndex), move);
            animalIndex = (animalIndex + 1) % animalCount;
        }
    }

    public Animal getAnimal(int index) {
        return animals.get(index);
    }
}
