package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.LinkedList;
import java.util.List;


public class OptionsParser {
    public static List<MoveDirection> parse(String[] args) {
        int i = 0;
        List<MoveDirection> directions = new LinkedList<>();
        for (String arg : args) {
            switch (arg) {
                case "f" -> directions.add(MoveDirection.FORWARD);
                case "b" -> directions.add(MoveDirection.BACKWARD);
                case "r" -> directions.add(MoveDirection.RIGHT);
                case "l" -> directions.add(MoveDirection.LEFT);
            }
        }
        return directions;
    }
}
