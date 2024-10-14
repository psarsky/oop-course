package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

public class World {
    public static void main(String[] args) {
        System.out.println("Start");

        MoveDirection[] dirs = OptionsParser.parse(args);
        run(dirs);

        System.out.println("Stop");
    }
    private static void run(MoveDirection[] dirs) {
        for (MoveDirection dir : dirs) {
            switch (dir) {
                case FORWARD -> System.out.println("Zwierzak idzie do przodu");
                case BACKWARD -> System.out.println("Zwierzak idzie do tyłu");
                case RIGHT -> System.out.println("Zwierzak idzie w prawo");
                case LEFT -> System.out.println("Zwierzak idzie w lewo");
            }
        }
    }
    /*
    private static void run(String[] args) {
        for (String arg : args) {
            switch (arg) {
                case "f" -> System.out.println("Zwierzak idzie do przodu");
                case "b" -> System.out.println("Zwierzak idzie do tyłu");
                case "r" -> System.out.println("Zwierzak idzie w prawo");
                case "l" -> System.out.println("Zwierzak idzie w lewo");
            }
        }
    }
    */
}
