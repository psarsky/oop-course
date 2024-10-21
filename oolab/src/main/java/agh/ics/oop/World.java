package agh.ics.oop;

import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

public class World {
    public static void main(String[] args) {
        /*
        System.out.println("Start");
        MoveDirection[] dirs = OptionsParser.parse(args);
        run(dirs);
        System.out.println("Stop");
         */
        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));
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
    private static void run(String[] args) {

        for (int i = 0; i < (args.length - 1); i++) {
            System.out.print(args[i] + ", ");
        }
        System.out.println(args[args.length - 1]);

        for (String arg : args) {
            switch (arg) {
                case "f" -> System.out.println("Zwierzak idzie do przodu");
                case "b" -> System.out.println("Zwierzak idzie do tyłu");
                case "r" -> System.out.println("Zwierzak idzie w prawo");
                case "l" -> System.out.println("Zwierzak idzie w lewo");
            }
        }
    }
}
