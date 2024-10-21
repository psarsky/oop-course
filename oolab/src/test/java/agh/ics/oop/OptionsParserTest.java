package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;


class OptionsParserTest {

    @Test
    void parseTest() {
        String[] input1 = {"f", "b", "r", "l"};
        MoveDirection[] expectedResult1 = {MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.LEFT};

        String[] input2 = {"f", "fdhbfv", "b", "eeee", "r", "", "l", "uwuwuewue"};
        MoveDirection[] expectedResult2 = {MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.LEFT};

        MoveDirection[] result1 = OptionsParser.parse(input1);
        MoveDirection[] result2 = OptionsParser.parse(input2);

        assertArrayEquals(expectedResult1, result1);
        assertArrayEquals(expectedResult2, result2);
    }
}
