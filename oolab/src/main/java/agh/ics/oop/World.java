package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;

import java.util.List;

public class World {

    public static void main(String[] args) {
        Animal janusz = new Animal();
        System.out.println(janusz.getPos());
    }

    private static void run(String[] args) {
        List<MoveDirection> dirs = OptionsParser.parse(args);
        for (MoveDirection dir : dirs) {
            switch (dir) {
                case FORWARD -> System.out.println("Zwierzak idzie do przodu");
                case BACKWARD -> System.out.println("Zwierzak idzie do tyłu");
                case RIGHT -> System.out.println("Zwierzak idzie w prawo");
                case LEFT -> System.out.println("Zwierzak idzie w lewo");
            }
        }
    }
}
