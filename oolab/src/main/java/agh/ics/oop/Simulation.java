package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.ArrayList;
import java.util.List;

public class Simulation {

    private final List<Animal> animals;
    private final List<MoveDirection> moves;

    public Simulation(List<Vector2d> initialPositions, List<MoveDirection> moves) {
        this.moves = moves;
        animals = new ArrayList<>();
        for (Vector2d initialPosition : initialPositions) {
            animals.add(new Animal(initialPosition));
        }
    }

    public void run() {
        int animalIndex = 0;
        int animalCount = animals.size();
        for (MoveDirection move : moves) {
            animals.get(animalIndex).move(move);
            System.out.println("Zwierzę " + animalIndex + ": " + animals.get(animalIndex).getPos());
            animalIndex = (animalIndex + 1) % animalCount;
        }
    }
}
